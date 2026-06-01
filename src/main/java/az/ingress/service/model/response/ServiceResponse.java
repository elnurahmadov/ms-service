package az.ingress.service.model.response;

import az.ingress.service.model.dto.Language;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private Language language;
}
