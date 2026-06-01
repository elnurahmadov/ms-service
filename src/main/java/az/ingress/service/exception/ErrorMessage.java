package az.ingress.service.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorMessage {
    UNEXPECTED_ERROR("error.unexpected"),
    VALIDATION_ERROR("error.validation.failed"),
    METHOD_NOT_ALLOWED("error.method.not.allowed"),
    SERVICES_GROUP_NOT_FOUND("error.servicesGroup.not.found"),
    DISPLAY_TEXT_NOT_FOUND("error.displayText.not.found"),
    SERVICE_NOT_FOUND("error.service.not.found");

    private final String value;
}