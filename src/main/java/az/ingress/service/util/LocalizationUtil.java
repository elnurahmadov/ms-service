package az.ingress.service.util;

import java.util.ResourceBundle;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

public enum LocalizationUtil {
    LOCALIZATION_UTIL;

    public String getMessageByKey(String bundle, String key) {
        return ResourceBundle.getBundle(bundle, getLocale()).getString(key);
    }

    public String getMessageByKey(String bundle, String key, Object... args) {
        return getMessageByKey(bundle, key).formatted(args);
    }
}