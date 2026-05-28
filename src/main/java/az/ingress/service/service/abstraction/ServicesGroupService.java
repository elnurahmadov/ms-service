package az.ingress.service.service.abstraction;

import az.ingress.service.model.request.CreateServicesGroupRequest;
import az.ingress.service.model.response.ServicesGroupResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface ServicesGroupService {
    void createServicesGroup(@Valid CreateServicesGroupRequest request);

    List<ServicesGroupResponse> getServicesGroup();

    ServicesGroupResponse getServicesGroup(Long id);
}
