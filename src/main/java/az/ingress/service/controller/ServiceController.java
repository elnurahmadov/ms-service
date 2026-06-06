package az.ingress.service.controller;

import az.ingress.service.model.request.CreateServiceRequest;
import az.ingress.service.model.response.ServiceDetailedResponse;
import az.ingress.service.model.response.ServiceResponse;
import az.ingress.service.service.abstraction.ServiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/v1/services")
@RequiredArgsConstructor
@Tag(name = "Services", description = "Services management operations")
public class ServiceController {

    private final ServiceService serviceService;

    @PostMapping
    @Operation(summary = "Create a new service", description = "Adds a new service to the system.")
    public ResponseEntity<Void> createService(@RequestBody @Valid CreateServiceRequest request) {
        serviceService.createService(request);
        return ResponseEntity.status(CREATED).build();
    }

    @GetMapping
    @Operation(summary = "Get all services", description = "Returns a list of all services available in the system.")
    public ResponseEntity<List<ServiceResponse>> getServices() {
        return ResponseEntity.ok(serviceService.getServices());
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get service by ID",
            description = "Returns detailed information about the service corresponding to the given ID.")
    public ResponseEntity<ServiceDetailedResponse> getServiceById(@PathVariable Long id) {
        return ResponseEntity.ok(serviceService.getServiceById(id));
    }

    @GetMapping("/by-group/{id}")
    @Operation(
            summary = "Get services belonging to a group",
            description = "Returns all services matching the given service group ID.")
    public ResponseEntity<List<ServiceResponse>> getServicesByGroupId(@PathVariable Long id) {
        return ResponseEntity.ok(serviceService.getServicesByGroupId(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a service", description = "Deletes the specified service by its identifier.")
    public ResponseEntity<Void> deleteService(@PathVariable Long id) {
        serviceService.deleteService(id);
        return ResponseEntity.noContent().build();
    }
}
