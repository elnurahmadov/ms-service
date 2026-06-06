package az.ingress.service.controller;

import az.ingress.service.model.dto.Language;
import az.ingress.service.model.request.CreateServicesGroupRequest;
import az.ingress.service.model.response.ServicesGroupResponse;
import az.ingress.service.service.abstraction.ServicesGroupService;
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
@RequestMapping("/v1/services-group")
@RequiredArgsConstructor
public class ServicesGroupController {

    private final ServicesGroupService servicesGroupService;

    @PostMapping
    public ResponseEntity<Void> createServicesGroup(@RequestBody @Valid CreateServicesGroupRequest request) {
        servicesGroupService.createServicesGroup(request);
        return ResponseEntity.status(CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<ServicesGroupResponse>> getServicesGroups() {
        return ResponseEntity.ok(servicesGroupService.getServicesGroups());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicesGroupResponse> getServicesGroup(@PathVariable Long id) {
        return ResponseEntity.ok(servicesGroupService.getServicesGroup(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateServicesGroup(@PathVariable Long id, @RequestBody @Valid Language language) {
        servicesGroupService.updateServicesGroup(id, language);
        return ResponseEntity.ok().build();
    }
}
