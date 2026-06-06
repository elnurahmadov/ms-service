package az.ingress.service.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Health Check", description = "Endpoints for service health monitoring")
public class HealthController {

    @GetMapping("/health")
    @Operation(
            summary = "Check service health status",
            description = "Verifies that the service is running and available. Returns HTTP 200 when the service is healthy."
    )
    public ResponseEntity<Void> checkHealthy() {
        return ResponseEntity.ok().build();
    }
}