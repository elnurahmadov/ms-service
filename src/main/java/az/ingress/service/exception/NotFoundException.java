package az.ingress.service.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(NOT_FOUND)
public class NotFoundException extends BaseApiException {
    public NotFoundException(ErrorMessage message, Object... arguments) {
        super(message, arguments);
    }
}