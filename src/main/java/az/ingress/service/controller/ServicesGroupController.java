package az.ingress.service.controller;

import az.ingress.service.model.request.CreateServicesGroupRequest;
import az.ingress.service.model.response.ServicesGroupResponse;
import az.ingress.service.service.abstraction.ServicesGroupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/v1/services-group")
@RequiredArgsConstructor
public class ServicesGroupController {

    private final ServicesGroupService servicesGroupService;

    @PostMapping
    @ResponseStatus(CREATED)
    public void createServicesGroup(@RequestBody @Valid CreateServicesGroupRequest request) {
        servicesGroupService.createServicesGroup(request);
    }

    @GetMapping
    public List<ServicesGroupResponse> getServicesGroup() {
        return servicesGroupService.getServicesGroup();
    }

    @GetMapping("/{id}")
    public ServicesGroupResponse getServicesGroup(@PathVariable Long id) {
        return servicesGroupService.getServicesGroup(id);
    }
}
