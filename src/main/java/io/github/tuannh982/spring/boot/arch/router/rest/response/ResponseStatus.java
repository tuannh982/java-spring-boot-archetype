package io.github.tuannh982.spring.boot.arch.router.rest.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

import java.io.Serializable;
import java.util.Date;

@Getter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ResponseStatus implements Serializable {
    private static final String DATE_FORMAT_ISO8601 = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    private final String code;
    private final String message;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_FORMAT_ISO8601)
    private final Date responseTime;

    public ResponseStatus(String code, String message) {
        this.code = code;
        this.message = message;
        this.responseTime = new Date();
    }
}
