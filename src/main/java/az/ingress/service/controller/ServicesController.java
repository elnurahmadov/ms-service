package az.ingress.service.controller;

import az.ingress.service.model.request.CreateServiceRequest;
import az.ingress.service.service.abstraction.ServicesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/v1/services")
@RequiredArgsConstructor
public class ServicesController {

    private final ServicesService servicesService;

    @PostMapping
    @ResponseStatus(CREATED)
    public void createService(@RequestBody @Valid CreateServiceRequest request) {
        servicesService.createService(request);
    }
}
