package az.ingress.service.model.request;

import az.ingress.service.model.dto.Language;
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
public class CreateServicesGroupRequest {

    @Valid
    @NotNull
    private Language language;
}