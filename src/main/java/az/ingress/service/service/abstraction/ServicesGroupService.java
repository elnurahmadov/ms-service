package az.ingress.service.service.abstraction;

import az.ingress.service.dao.entity.ServicesGroupEntity;
import az.ingress.service.model.dto.Language;
import az.ingress.service.model.request.CreateServicesGroupRequest;
import az.ingress.service.model.response.ServicesGroupResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface ServicesGroupService {
    void createServicesGroup(@Valid CreateServicesGroupRequest request);

    List<ServicesGroupResponse> getServicesGroups();

    ServicesGroupResponse getServicesGroup(Long id);

    ServicesGroupEntity getServicesGroupEntity(Long id);

    void updateServicesGroup(Long id, @Valid Language language);
}
