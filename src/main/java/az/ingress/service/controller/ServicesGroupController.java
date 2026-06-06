package az.ingress.service.controller;

import az.ingress.service.model.dto.Language;
import az.ingress.service.model.request.CreateServicesGroupRequest;
import az.ingress.service.model.response.ServicesGroupResponse;
import az.ingress.service.service.abstraction.ServicesGroupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/v1/services-groups")
@RequiredArgsConstructor
@Tag(name = "Service Groups", description = "Endpoints for managing service groups")
public class ServicesGroupController {

    private final ServicesGroupService servicesGroupService;

    @PostMapping
    @Operation(
            summary = "Create a service group",
            description = "Creates a new service group with the provided information."
    )
    public ResponseEntity<Void> createServicesGroup(@RequestBody @Valid CreateServicesGroupRequest request) {
        servicesGroupService.createServicesGroup(request);
        return ResponseEntity.status(CREATED).build();
    }

    @GetMapping
    @Operation(summary = "Get all service groups", description = "Returns a list of all available service groups."
    )
    public ResponseEntity<List<ServicesGroupResponse>> getServicesGroups() {
        return ResponseEntity.ok(servicesGroupService.getServicesGroups());
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get a service group by ID",
            description = "Returns detailed information about the specified service group."
    )
    public ResponseEntity<ServicesGroupResponse> getServicesGroup(@PathVariable Long id) {
        return ResponseEntity.ok(servicesGroupService.getServicesGroup(id));
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update a service group",
            description = "Updates the language-specific information of the specified service group."
    )
    public ResponseEntity<Void> updateServicesGroup(@PathVariable Long id,
                                                    @RequestBody @Valid Language language) {
        servicesGroupService.updateServicesGroup(id, language);
        return ResponseEntity.ok().build();
    }
}
