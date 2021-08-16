package io.github.tuannh982.spring.boot.arch.router.rest;

import io.github.tuannh982.spring.boot.arch.commons.text.TextUtils;
import io.github.tuannh982.spring.boot.arch.exception.DomainException;
import io.github.tuannh982.spring.boot.arch.router.rest.response.GeneralResponse;
import io.github.tuannh982.spring.boot.arch.router.rest.response.HttpResponseGenerator;
import io.github.tuannh982.spring.boot.arch.router.rest.response.ResponseStatus;
import io.github.tuannh982.spring.boot.arch.router.rest.response.ResponseStatusCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Locale;

@Slf4j
@ControllerAdvice
public class GlobalRouterExceptionHandler extends ResponseEntityExceptionHandler {
    private final ResourceBundleMessageSource errorMessageBundle;
    private final HttpResponseGenerator responseGenerator;

    public GlobalRouterExceptionHandler(
            @Qualifier("error-message-bundle") ResourceBundleMessageSource errorMessageBundle,
            HttpResponseGenerator responseGenerator
    ) {
        this.errorMessageBundle = errorMessageBundle;
        this.responseGenerator = responseGenerator;
    }

    @ExceptionHandler({DomainException.class})
    public final ResponseEntity<? extends GeneralResponse<?>> handleDomainExceptions(DomainException e) {
        log.error(e.getMessage(), e);
        Locale locale = LocaleContextHolder.getLocale();
        String message = e.getMessage();
        ResponseStatus responseStatus = null;
        if (StringUtils.isEmpty(message)) {
            message = errorMessageBundle.getMessage(e.code(), null, locale);
            if (e.values() == null) {
                responseStatus = new ResponseStatus(e.code(), message);
            } else {
                responseStatus = new ResponseStatus(e.code(), TextUtils.substitute(message, e.values()));
            }
        } else {
            responseStatus = new ResponseStatus(e.code(), message);
        }
        GeneralResponse<Object> responseData = new GeneralResponse<>(responseStatus, e.data());
        return ResponseEntity.ok(responseData);
    }

    @ExceptionHandler({Exception.class, RuntimeException.class})
    public final ResponseEntity<? extends GeneralResponse<?>> handleAllUncaughtExceptions(Exception e) {
        log.error(e.getMessage(), e);
        return responseGenerator.fail(ResponseStatusCode.INTERNAL_ERROR);
    }
}
