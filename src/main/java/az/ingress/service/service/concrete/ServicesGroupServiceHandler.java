package az.ingress.service.service.concrete;

import az.ingress.service.aspect.ExecutionTracker;
import az.ingress.service.dao.entity.DisplayTextEntity;
import az.ingress.service.dao.entity.ServicesGroupEntity;
import az.ingress.service.dao.repository.DisplayTextRepository;
import az.ingress.service.dao.repository.ServicesGroupRepository;
import az.ingress.service.exception.NotFoundException;
import az.ingress.service.logger.ApplicationLogger;
import az.ingress.service.model.request.CreateServicesGroupRequest;
import az.ingress.service.model.response.ServicesGroupResponse;
import az.ingress.service.service.abstraction.ServicesGroupService;
import az.ingress.service.util.CacheUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static az.ingress.service.exception.ErrorMessage.SERVICES_GROUP_NOT_FOUND;
import static az.ingress.service.mapper.DisplayTextMapper.DISPLAY_TEXT_MAPPER;
import static az.ingress.service.mapper.ServicesGroupMapper.SERVICES_GROUP_MAPPER;
import static az.ingress.service.model.constants.Cache.CACHE_EXPIRATION_HOURS;
import static az.ingress.service.model.constants.Cache.SERVICE_GROUP_CACHE_KEY;
import static az.ingress.service.model.enums.DisplayTextColumn.SERVICES_GROUP_DISPLAY_TEXT_ID;
import static java.time.temporal.ChronoUnit.HOURS;

@Service
@RequiredArgsConstructor
@ExecutionTracker
public class ServicesGroupServiceHandler implements ServicesGroupService {

    private final ApplicationLogger logger = ApplicationLogger.getLogger(ServicesGroupServiceHandler.class);

    private final ServicesGroupRepository servicesGroupRepository;
    private final DisplayTextRepository displayTextRepository;
    private final CacheUtil cacheUtil;

    @Override
    @Transactional
    public void createServicesGroup(CreateServicesGroupRequest request) {

        DisplayTextEntity displayText = DISPLAY_TEXT_MAPPER.toEntity(
                request,
                SERVICES_GROUP_DISPLAY_TEXT_ID
        );

        displayText = displayTextRepository.save(displayText);
        ServicesGroupEntity servicesGroup = ServicesGroupEntity.builder()
                .displayTextEntity(displayText)
                .build();

        servicesGroupRepository.save(servicesGroup);
        clearAllCaches();
    }

    @Override
    public List<ServicesGroupResponse> getServicesGroup() {

        List<ServicesGroupResponse> response;

        response = cacheUtil.getBucket(SERVICE_GROUP_CACHE_KEY);

        if (response != null) {
            return response;
        }

        List<ServicesGroupEntity> servicesGroupEntities = servicesGroupRepository.findAll();
        response = SERVICES_GROUP_MAPPER.toResponseList(servicesGroupEntities);

        cacheUtil.saveToCache(SERVICE_GROUP_CACHE_KEY, response, CACHE_EXPIRATION_HOURS, HOURS);

        return response;
    }

    @Override
    public ServicesGroupResponse getServicesGroup(Long id) {
        return SERVICES_GROUP_MAPPER.toResponse(fetchServicesGroupIfExist(id));
    }

    private ServicesGroupEntity fetchServicesGroupIfExist(Long id) {
        return servicesGroupRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Services Group not found with id: {}", id);
                    return new NotFoundException(SERVICES_GROUP_NOT_FOUND, id);
                });
    }

    private void clearAllCaches() {
        cacheUtil.deleteKey(SERVICE_GROUP_CACHE_KEY);
    }
}
