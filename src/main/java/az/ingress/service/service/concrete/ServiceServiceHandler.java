package az.ingress.service.service.concrete;

import az.ingress.service.aspect.ExecutionTracker;
import az.ingress.service.dao.entity.ServiceEntity;
import az.ingress.service.dao.repository.ServiceRepository;
import az.ingress.service.exception.NotFoundException;
import az.ingress.service.logger.ApplicationLogger;
import az.ingress.service.model.request.CreateServiceRequest;
import az.ingress.service.model.response.ServiceDetailedResponse;
import az.ingress.service.model.response.ServiceResponse;
import az.ingress.service.service.abstraction.DisplayTextService;
import az.ingress.service.service.abstraction.ServiceService;
import az.ingress.service.service.abstraction.ServicesGroupService;
import az.ingress.service.util.CacheUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static az.ingress.service.exception.ErrorMessage.SERVICE_NOT_FOUND;
import static az.ingress.service.mapper.ServiceMapper.SERVICE_MAPPER;
import static az.ingress.service.model.constants.Cache.CACHE_EXPIRATION_HOURS;
import static az.ingress.service.model.constants.Cache.SERVICES_CACHE_KEY;
import static az.ingress.service.model.enums.DisplayTextColumn.SERVICES_DISPLAY_TEXT_ID;
import static java.time.temporal.ChronoUnit.HOURS;

@Service
@RequiredArgsConstructor
@ExecutionTracker
public class ServiceServiceHandler implements ServiceService {

    private final ApplicationLogger logger = ApplicationLogger.getLogger(ServiceServiceHandler.class);

    private final ServicesGroupService servicesGroupService;
    private final DisplayTextService displayTextService;
    private final ServiceRepository serviceRepository;
    private final CacheUtil cacheUtil;

    @Override
    @Transactional
    public void createService(CreateServiceRequest request) {

        var servicesGroup = servicesGroupService.getServicesGroupEntity(request.getServicesGroupId());

        var displayText = displayTextService.createDisplayText(
                request.getLanguage(),
                SERVICES_DISPLAY_TEXT_ID
        );

        var serviceEntity = SERVICE_MAPPER.toEntity(
                request,
                displayText,
                servicesGroup
        );

        serviceRepository.save(serviceEntity);
        clearAllCaches();
    }

    @Override
    public List<ServiceResponse> getServices() {

        List<ServiceResponse> response;

        response = cacheUtil.getBucket(SERVICES_CACHE_KEY);

        if (response != null) {
            return response;
        }

        var servicesEntities = serviceRepository.findAll();
        response = SERVICE_MAPPER.toResponseList(servicesEntities);

        cacheUtil.saveToCache(SERVICES_CACHE_KEY, response, CACHE_EXPIRATION_HOURS, HOURS);

        return response;
    }

    @Override
    public ServiceDetailedResponse getServiceById(Long id) {
        return SERVICE_MAPPER.toDetailedResponse(fetchServiceIfExist(id));
    }

    @Override
    public List<ServiceResponse> getServicesByGroupId(Long id) {
        var servicesGroup = servicesGroupService.getServicesGroupEntity(id);
        var servicesEntities = serviceRepository.getServicesByServicesGroupId(servicesGroup.getId());

        return SERVICE_MAPPER.toResponseList(servicesEntities);
    }

    @Override
    @Transactional
    public void deleteService(Long id) {
        var serviceEntity = fetchServiceIfExist(id);

        var displayTextId = serviceEntity.getDisplayText().getId();

        serviceRepository.delete(serviceEntity);
        displayTextService.deleteDisplayText(displayTextId);

        clearAllCaches();
    }

    private ServiceEntity fetchServiceIfExist(Long id) {
        return serviceRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Service with id {} not found", id);
                    return new NotFoundException(SERVICE_NOT_FOUND, id);
                });
    }

    private void clearAllCaches() {
        cacheUtil.deleteKey(SERVICES_CACHE_KEY);
    }
}
