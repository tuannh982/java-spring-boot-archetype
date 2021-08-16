package io.github.tuannh982.spring.boot.arch.router.rest.response;

import java.util.HashMap;
import java.util.Map;

public interface ResponseStatusCode {
    ResponseStatusCode INTERNAL_ERROR = new ResponseStatusCode() {
        @Override
        public String getResponseCode() {
            return "INTERNAL_ERROR";
        }

        @Override
        public int getHttpCode() {
            return 500;
        }
    };
    Map<String, ResponseStatusCode> map = new HashMap<>();

    String getResponseCode();
    int getHttpCode();

    static ResponseStatusCode get(String responseCode) {
        return map.getOrDefault(responseCode, INTERNAL_ERROR);
    }
}
