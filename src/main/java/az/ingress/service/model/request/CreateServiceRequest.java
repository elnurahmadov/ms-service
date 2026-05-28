package az.ingress.service.model.request;

import az.ingress.service.model.dto.Language;
import az.ingress.service.model.enums.ServiceType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateServiceRequest {

    @Valid
    @NotNull
    private Language language;

    @NotNull
    private Long servicesGroupId;

    private Boolean descriptionApplicable;
    private Boolean actionApplicable;
    private Boolean rateApplicable;
    private Boolean tooltipsApplicable;
    private Boolean reportApplicable;
    private Boolean restrictionApplicable;

    @NotNull
    private ServiceType serviceType;
}
