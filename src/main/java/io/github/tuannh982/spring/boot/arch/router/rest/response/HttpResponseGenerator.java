package io.github.tuannh982.spring.boot.arch.router.rest.response;

import io.github.tuannh982.spring.boot.arch.commons.text.TextUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Map;

@Component
public class HttpResponseGenerator {
    private final ResourceBundleMessageSource errorMessageBundle;

    public HttpResponseGenerator(@Qualifier("error-message-bundle") ResourceBundleMessageSource errorMessageBundle) {
        this.errorMessageBundle = errorMessageBundle;
    }

    public ResponseStatus responseStatus(String code, Map<String, String> values) {
        Locale locale = LocaleContextHolder.getLocale();
        String template = errorMessageBundle.getMessage(code, null, locale);
        if (values == null) {
            return new ResponseStatus(code, template);
        } else {
            return new ResponseStatus(code, TextUtils.substitute(template, values));
        }
    }

    public <T> ResponseEntity<GeneralResponse<T>> success(T data, Map<String, String> values) {
        ResponseStatusCode responseStatusCode = BaseResponseStatusCode.SUCCESS;
        GeneralResponse<T> response = new GeneralResponse<>(responseStatus(responseStatusCode.getResponseCode(), values), data);
        return ResponseEntity.status(responseStatusCode.getHttpCode()).body(response);
    }

    public <T> ResponseEntity<GeneralResponse<T>> success(T data) {
        return success(data, null);
    }

    public <T> ResponseEntity<GeneralResponse<T>> fail(ResponseStatusCode responseStatusCode, T data, Map<String, String> values) {
        GeneralResponse<T> response = new GeneralResponse<>(responseStatus(responseStatusCode.getResponseCode(), values), data);
        return ResponseEntity.status(responseStatusCode.getHttpCode()).body(response);
    }

    public <T> ResponseEntity<GeneralResponse<T>> fail(ResponseStatusCode responseStatusCode, Map<String, String> values) {
        return fail(responseStatusCode, null, values);
    }

    public <T> ResponseEntity<GeneralResponse<T>> fail(ResponseStatusCode responseStatusCode) {
        return fail(responseStatusCode, null, null);
    }

    public <T> ResponseEntity<GeneralResponse<T>> fail(ResponseStatusCode responseStatusCode, T data) {
        return fail(responseStatusCode, data, null);
    }
}
