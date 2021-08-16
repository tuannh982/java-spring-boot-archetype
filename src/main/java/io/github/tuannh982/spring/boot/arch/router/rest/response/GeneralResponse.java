package io.github.tuannh982.spring.boot.arch.router.rest.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GeneralResponse<T> {
    private ResponseStatus status;
    private T data;
}
