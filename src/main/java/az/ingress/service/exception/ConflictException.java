package az.ingress.service.exception;

public class ConflictException extends BaseApiException {

    public ConflictException(ErrorMessage message, Object... arguments) {
        super(message, arguments);
    }
}