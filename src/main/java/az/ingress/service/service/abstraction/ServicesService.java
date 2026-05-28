package az.ingress.service.service.abstraction;

import az.ingress.service.model.request.CreateServiceRequest;
import jakarta.validation.Valid;

public interface ServicesService {
    void createService(@Valid CreateServiceRequest request);
}
