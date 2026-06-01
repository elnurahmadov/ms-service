package az.ingress.service.exception;

import az.ingress.service.logger.ApplicationLogger;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static az.ingress.service.exception.ErrorMessage.UNEXPECTED_ERROR;
import static az.ingress.service.exception.ErrorMessage.VALIDATION_ERROR;
import static az.ingress.service.model.constants.LocalizationConstants.ERROR_BUNDLE;
import static az.ingress.service.model.constants.LocalizationConstants.VALIDATION_BUNDLE;
import static az.ingress.service.util.LocalizationUtil.LOCALIZATION_UTIL;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED;

@RestControllerAdvice
public class ErrorHandler {
    private final ApplicationLogger log = ApplicationLogger.getLogger(ErrorHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handle(Exception ex) {
        log.error("Exception: ", ex);
        var message = LOCALIZATION_UTIL.getMessageByKey(ERROR_BUNDLE, UNEXPECTED_ERROR.getValue());

        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(
                ErrorResponse.builder()
                        .message(message)
                        .build()
        );
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handle(HttpRequestMethodNotSupportedException ex) {
        log.error("HttpRequestMethodNotSupportedException: ", ex);
        var message = LOCALIZATION_UTIL.getMessageByKey(ERROR_BUNDLE, ErrorMessage.METHOD_NOT_ALLOWED.getValue());

        return ResponseEntity.status(METHOD_NOT_ALLOWED).body(
                ErrorResponse.builder()
                        .message(message)
                        .build()
        );
    }

    @ExceptionHandler(BaseApiException.class)
    public ResponseEntity<ErrorResponse> handle(BaseApiException ex) {
        log.error("BaseApiException: ", ex);

        var status = AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class);
        var httpStatus = (status != null) ? status.value() : INTERNAL_SERVER_ERROR;

        var message = ex.getArguments().length > 0
                ? LOCALIZATION_UTIL.getMessageByKey(ERROR_BUNDLE, ex.getErrorMessage().getValue(), ex.getArguments())
                : LOCALIZATION_UTIL.getMessageByKey(ERROR_BUNDLE, ex.getErrorMessage().getValue());

        return ResponseEntity.status(httpStatus).body(
                ErrorResponse.builder()
                        .message(message)
                        .build()
        );
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponse> handle(BindException ex) {
        log.error("BindException: ", ex);

        var errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> {
                    var fieldName = error.getField();
                    var errorKey = error.getDefaultMessage();
                    var errorMessage = LOCALIZATION_UTIL.getMessageByKey(VALIDATION_BUNDLE, errorKey);
                    return fieldName + ": " + errorMessage;
                })
                .toList();

        var message = LOCALIZATION_UTIL.getMessageByKey(ERROR_BUNDLE, VALIDATION_ERROR.getValue());

        return ResponseEntity.status(BAD_REQUEST).body(
                ErrorResponse.builder()
                        .message(message)
                        .errors(errors)
                        .build()
        );
    }
}