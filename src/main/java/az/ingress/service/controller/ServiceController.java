package az.ingress.service.controller;

import az.ingress.service.model.request.CreateServiceRequest;
import az.ingress.service.model.response.ServiceDetailedResponse;
import az.ingress.service.model.response.ServiceResponse;
import az.ingress.service.service.abstraction.ServiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
public class ServiceController {

    private final ServiceService serviceService;

    @PostMapping
    public ResponseEntity<Void> createService(@RequestBody @Valid CreateServiceRequest request) {
        serviceService.createService(request);
        return ResponseEntity.status(CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<ServiceResponse>> getServices() {
        return ResponseEntity.ok(serviceService.getServices());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceDetailedResponse> getServiceById(@PathVariable Long id) {
        return ResponseEntity.ok(serviceService.getServiceById(id));
    }

    @GetMapping("/by-group/{id}")
    public ResponseEntity<List<ServiceResponse>> getServicesByGroupId(@PathVariable Long id) {
        return ResponseEntity.ok(serviceService.getServicesByGroupId(id));
    }
}
