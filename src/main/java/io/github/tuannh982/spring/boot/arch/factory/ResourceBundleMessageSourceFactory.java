package io.github.tuannh982.spring.boot.arch.factory;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.context.support.ResourceBundleMessageSource;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResourceBundleMessageSourceFactory {
    public static ResourceBundleMessageSource messageSource(String bundle) {
        ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
        resourceBundleMessageSource.setBasename(bundle);
        resourceBundleMessageSource.setDefaultEncoding("UTF-8");
        resourceBundleMessageSource.setUseCodeAsDefaultMessage(true);
        return resourceBundleMessageSource;
    }
}
