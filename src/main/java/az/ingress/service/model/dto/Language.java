package az.ingress.service.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

import static az.ingress.service.model.constants.ValidationConstants.LANGUAGE_AZ_NOT_BLANK;
import static az.ingress.service.model.constants.ValidationConstants.LANGUAGE_EN_NOT_BLANK;
import static az.ingress.service.model.constants.ValidationConstants.LANGUAGE_RU_NOT_BLANK;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Language implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @NotBlank(message = LANGUAGE_AZ_NOT_BLANK)
    private String az;

    @NotBlank(message = LANGUAGE_EN_NOT_BLANK)
    private String en;

    @NotBlank(message = LANGUAGE_RU_NOT_BLANK)
    private String ru;
}
