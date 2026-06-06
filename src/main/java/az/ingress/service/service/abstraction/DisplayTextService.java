package az.ingress.service.service.abstraction;

import az.ingress.service.dao.entity.DisplayTextEntity;
import az.ingress.service.model.dto.Language;
import az.ingress.service.model.enums.DisplayTextColumn;

public interface DisplayTextService {
    DisplayTextEntity createDisplayText(Language language, DisplayTextColumn column);

    void updateDisplayText(Long id, Language language);

    void deleteDisplayText(Long id);
}
