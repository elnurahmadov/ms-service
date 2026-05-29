package az.ingress.service.service.concrete;

import az.ingress.service.aspect.ExecutionTracker;
import az.ingress.service.dao.entity.DisplayTextEntity;
import az.ingress.service.dao.entity.ServicesEntity;
import az.ingress.service.dao.repository.ServicesRepository;
import az.ingress.service.model.request.CreateServiceRequest;
import az.ingress.service.model.response.ServicesResponse;
import az.ingress.service.service.abstraction.DisplayTextService;
import az.ingress.service.service.abstraction.ServicesGroupService;
import az.ingress.service.service.abstraction.ServicesService;
import az.ingress.service.util.CacheUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static az.ingress.service.mapper.ServicesMapper.SERVICES_MAPPER;
import static az.ingress.service.model.constants.Cache.CACHE_EXPIRATION_HOURS;
import static az.ingress.service.model.constants.Cache.SERVICES_CACHE_KEY;
import static az.ingress.service.model.enums.DisplayTextColumn.SERVICES_GROUP_DISPLAY_TEXT_ID;
import static java.time.temporal.ChronoUnit.HOURS;

@Service
@RequiredArgsConstructor
@ExecutionTracker
public class ServicesServiceHandler implements ServicesService {

    private final ServicesGroupService servicesGroupService;
    private final DisplayTextService displayTextService;
    private final ServicesRepository servicesRepository;
    private final CacheUtil cacheUtil;

    @Override
    @Transactional
    public void createService(CreateServiceRequest request) {

        var servicesGroup = servicesGroupService.getServicesGroupEntity(request.getServicesGroupId());

        DisplayTextEntity displayText = displayTextService.createDisplayText(
                request.getLanguage(),
                SERVICES_GROUP_DISPLAY_TEXT_ID
        );

        ServicesEntity servicesEntity = SERVICES_MAPPER.toEntity(
                request,
                displayText,
                servicesGroup
        );

        servicesRepository.save(servicesEntity);
        clearAllCaches();
    }

    @Override
    public List<ServicesResponse> getServices() {

        List<ServicesResponse> response;

        response = cacheUtil.getBucket(SERVICES_CACHE_KEY);

        if (response != null) {
            return response;
        }

        List<ServicesEntity> servicesEntities = servicesRepository.findAll();
        response = SERVICES_MAPPER.toResponseList(servicesEntities);

        cacheUtil.saveToCache(SERVICES_CACHE_KEY, response, CACHE_EXPIRATION_HOURS, HOURS);

        return response;
    }

    private void clearAllCaches() {
        cacheUtil.deleteKey(SERVICES_CACHE_KEY);
    }
}
