package az.ingress.service.model.constants;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class Cache {
    public static final int CACHE_EXPIRATION_HOURS = 24;
    public static final String SERVICES_GROUP_CACHE_KEY = "ms-service::services_group";
    public static final String SERVICES_CACHE_KEY = "ms-service::services";
}
