package io.github.tuannh982.spring.boot.arch.commons.text;

import lombok.NoArgsConstructor;
import org.apache.commons.text.StringSubstitutor;

import java.util.Map;

@NoArgsConstructor
public class TextUtils {
    public static String substitute(String template, Map<String, String> values) {
        StringSubstitutor substitutor = new StringSubstitutor(values);
        return substitutor.replace(template);
    }
}
