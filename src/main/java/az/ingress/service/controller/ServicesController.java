package az.ingress.service.controller;

import az.ingress.service.model.request.CreateServiceRequest;
import az.ingress.service.model.response.ServicesResponse;
import az.ingress.service.service.abstraction.ServicesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/v1/services")
@RequiredArgsConstructor
public class ServicesController {

    private final ServicesService servicesService;

    @PostMapping
    public ResponseEntity<Void> createService(@RequestBody @Valid CreateServiceRequest request) {
        servicesService.createService(request);
        return ResponseEntity.status(CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<ServicesResponse>> getServices() {
        return ResponseEntity.ok(servicesService.getServices());
    }
}
