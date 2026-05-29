package az.ingress.service.service.concrete;

import az.ingress.service.aspect.ExecutionTracker;
import az.ingress.service.dao.entity.DisplayTextEntity;
import az.ingress.service.dao.repository.DisplayTextRepository;
import az.ingress.service.exception.NotFoundException;
import az.ingress.service.logger.ApplicationLogger;
import az.ingress.service.model.dto.Language;
import az.ingress.service.model.enums.DisplayTextColumn;
import az.ingress.service.service.abstraction.DisplayTextService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static az.ingress.service.exception.ErrorMessage.DISPLAY_TEXT_NOT_FOUND;
import static az.ingress.service.mapper.DisplayTextMapper.DISPLAY_TEXT_MAPPER;

@Service
@RequiredArgsConstructor
@ExecutionTracker
public class DisplayTextServiceHandler implements DisplayTextService {

    private final ApplicationLogger logger = ApplicationLogger.getLogger(DisplayTextServiceHandler.class);

    private final DisplayTextRepository displayTextRepository;

    @Override
    public DisplayTextEntity createDisplayText(Language language,
                                               DisplayTextColumn column) {

        DisplayTextEntity displayText = DISPLAY_TEXT_MAPPER.toEntity(
                language,
                column
        );

        return displayTextRepository.save(displayText);
    }

    @Override
    @Transactional
    public void updateDisplayText(Long id, Language language) {

        DisplayTextEntity displayText = fetchDisplayTextIfExist(id);

        displayText.setAz(language.getAz());
        displayText.setEn(language.getEn());
        displayText.setRu(language.getRu());
    }

    private DisplayTextEntity fetchDisplayTextIfExist(Long id) {

        return displayTextRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Display Text not found with id: {}", id);
                    return new NotFoundException(DISPLAY_TEXT_NOT_FOUND, id);
                });
    }
}