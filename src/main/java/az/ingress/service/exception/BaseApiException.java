package az.ingress.service.exception;

import lombok.Getter;

@Getter
public abstract class BaseApiException extends RuntimeException {
    private final ErrorMessage errorMessage;
    private final transient Object[] arguments;

    protected BaseApiException(ErrorMessage errorMessage, Object... arguments) {
        super(errorMessage.getValue());
        this.errorMessage = errorMessage;
        this.arguments = arguments;
    }
}