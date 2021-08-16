package io.github.tuannh982.spring.boot.arch.router.rest.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BaseResponseStatusCode implements ResponseStatusCode {
    SUCCESS("00", 200);

    // -----------------------------------------------------------------------------------------------------------------
    private final String responseCode;
    private final int httpCode;

    static {
        for (BaseResponseStatusCode entry : BaseResponseStatusCode.values()) {
            map.put(entry.getResponseCode(), entry);
        }
    }
}
