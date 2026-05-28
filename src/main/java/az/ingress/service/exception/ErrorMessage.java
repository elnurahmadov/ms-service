package az.ingress.service.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorMessage {
    UNEXPECTED_ERROR("error.servicesGroup.unexpected"),
    SERVICES_GROUP_NOT_FOUND("error.servicesGroup.not.found"),
    VALIDATION_ERROR("error.servicesGroup.validation.failed"),
    SERVICES_GROUP_METHOD_NOT_ALLOWED("error.servicesGroup.method.not.allowed");

    private final String value;
}