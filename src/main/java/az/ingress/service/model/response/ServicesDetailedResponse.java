package az.ingress.service.model.response;

import az.ingress.service.model.dto.Language;
import az.ingress.service.model.enums.ServiceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServicesDetailedResponse {
    private Long id;
    private Language language;
    private Long servicesGroupId;
    private String serviceCode;
    private boolean descriptionApplicable;
    private boolean actionApplicable;
    private boolean rateApplicable;
    private boolean tooltipsApplicable;
    private boolean reportApplicable;
    private boolean restrictionApplicable;
    private ServiceType serviceType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
