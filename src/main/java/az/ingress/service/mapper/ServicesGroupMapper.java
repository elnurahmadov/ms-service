package az.ingress.service.mapper;

import az.ingress.service.dao.entity.DisplayTextEntity;
import az.ingress.service.dao.entity.ServicesGroupEntity;
import az.ingress.service.model.dto.Language;
import az.ingress.service.model.response.ServicesGroupResponse;

import java.util.List;

public enum ServicesGroupMapper {
    SERVICES_GROUP_MAPPER;

    public List<ServicesGroupResponse> toResponseList(List<ServicesGroupEntity> servicesGroupEntities) {

        return servicesGroupEntities.stream().map(this::toResponse).toList();
    }

    public ServicesGroupResponse toResponse(ServicesGroupEntity entity) {

        DisplayTextEntity displayTextEntity = entity.getDisplayTextEntity();

        return ServicesGroupResponse.builder()
                .id(entity.getId())
                .language(Language.builder()
                        .az(displayTextEntity.getAz())
                        .en(displayTextEntity.getEn())
                        .ru(displayTextEntity.getRu())
                        .build())
                .build();
    }
}
