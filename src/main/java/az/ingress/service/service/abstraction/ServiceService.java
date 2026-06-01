package az.ingress.service.service.abstraction;

import az.ingress.service.model.request.CreateServiceRequest;
import az.ingress.service.model.response.ServiceDetailedResponse;
import az.ingress.service.model.response.ServiceResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface ServiceService {
    void createService(@Valid CreateServiceRequest request);

    List<ServiceResponse> getServices();

    ServiceDetailedResponse getServiceById(Long id);
}
