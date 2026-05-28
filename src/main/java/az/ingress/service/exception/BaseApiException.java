package az.ingress.service.exception;

import static az.ingress.service.model.constants.LocalizationConstants.ERROR_BUNDLE;
import static az.ingress.service.util.LocalizationUtil.LOCALIZATION_UTIL;

public abstract class BaseApiException extends RuntimeException {

    protected BaseApiException(ErrorMessage message, Object... arguments) {
        super(LOCALIZATION_UTIL.getMessageByKey(ERROR_BUNDLE, message.getValue()).formatted(arguments));
    }
}