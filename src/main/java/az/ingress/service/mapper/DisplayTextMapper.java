package az.ingress.service.mapper;

import az.ingress.service.dao.entity.DisplayTextEntity;
import az.ingress.service.model.dto.Language;
import az.ingress.service.model.enums.DisplayTextColumn;

public enum DisplayTextMapper {
    DISPLAY_TEXT_MAPPER;

    public DisplayTextEntity toEntity(Language language,
                                      DisplayTextColumn column) {

        return DisplayTextEntity.builder()
                .columnName(column.name())
                .az(language.getAz())
                .en(language.getEn())
                .ru(language.getRu())
                .build();
    }
}
