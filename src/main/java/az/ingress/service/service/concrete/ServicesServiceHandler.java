package az.ingress.service.service.concrete;

import az.ingress.service.aspect.ExecutionTracker;
import az.ingress.service.dao.entity.DisplayTextEntity;
import az.ingress.service.dao.entity.ServicesEntity;
import az.ingress.service.dao.repository.DisplayTextRepository;
import az.ingress.service.dao.repository.ServicesRepository;
import az.ingress.service.model.request.CreateServiceRequest;
import az.ingress.service.model.response.ServicesResponse;
import az.ingress.service.service.abstraction.ServicesGroupService;
import az.ingress.service.service.abstraction.ServicesService;
import az.ingress.service.util.CacheUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static az.ingress.service.mapper.DisplayTextMapper.DISPLAY_TEXT_MAPPER;
import static az.ingress.service.mapper.ServicesMapper.SERVICES_MAPPER;
import static az.ingress.service.model.constants.Cache.CACHE_EXPIRATION_HOURS;
import static az.ingress.service.model.constants.Cache.SERVICE_CACHE_KEY;
import static az.ingress.service.model.enums.DisplayTextColumn.SERVICES_DISPLAY_TEXT_ID;
import static java.time.temporal.ChronoUnit.HOURS;

@Service
@RequiredArgsConstructor
@ExecutionTracker
public class ServicesServiceHandler implements ServicesService {

    private final ServicesGroupService servicesGroupService;
    private final DisplayTextRepository displayTextRepository;
    private final ServicesRepository servicesRepository;
    private final CacheUtil cacheUtil;

    @Override
    @Transactional
    public void createService(CreateServiceRequest request) {

        var servicesGroup = servicesGroupService.getServicesGroupEntity(request.getServicesGroupId());

        DisplayTextEntity displayText = DISPLAY_TEXT_MAPPER.toEntity(
                request.getLanguage(),
                SERVICES_DISPLAY_TEXT_ID
        );

        displayText = displayTextRepository.save(displayText);

        ServicesEntity servicesEntity = SERVICES_MAPPER.toEntity(
                request,
                displayText,
                servicesGroup
        );

        servicesRepository.save(servicesEntity);
    }

    @Override
    public List<ServicesResponse> getServices() {

        List<ServicesResponse> response;

        response = cacheUtil.getBucket(SERVICE_CACHE_KEY);

        if (response != null) {
            return response;
        }

        List<ServicesEntity> servicesEntities = servicesRepository.findAll();
        response = SERVICES_MAPPER.toResponseList(servicesEntities);

        cacheUtil.saveToCache(SERVICE_CACHE_KEY, response, CACHE_EXPIRATION_HOURS, HOURS);

        return response;
    }
}
