package az.ingress.service.exception;

public class NotFoundException extends BaseApiException {

    public NotFoundException(ErrorMessage message, Object... arguments) {
        super(message, arguments);
    }
}