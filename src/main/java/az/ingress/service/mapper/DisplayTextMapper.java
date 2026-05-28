package az.ingress.service.mapper;

import az.ingress.service.dao.entity.DisplayTextEntity;
import az.ingress.service.model.enums.DisplayTextColumn;
import az.ingress.service.model.request.CreateServicesGroupRequest;

public enum DisplayTextMapper {
    DISPLAY_TEXT_MAPPER;

    public DisplayTextEntity toEntity(CreateServicesGroupRequest request,
                                      DisplayTextColumn column) {

        return DisplayTextEntity.builder()
                .columnName(column.name())
                .az(request.getLanguage().getAz())
                .en(request.getLanguage().getEn())
                .ru(request.getLanguage().getRu())
                .build();
    }
}
