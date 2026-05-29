package az.ingress.service.service.abstraction;

import az.ingress.service.model.request.CreateServiceRequest;
import az.ingress.service.model.response.ServicesResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface ServicesService {
    void createService(@Valid CreateServiceRequest request);

    List<ServicesResponse> getServices();
}
