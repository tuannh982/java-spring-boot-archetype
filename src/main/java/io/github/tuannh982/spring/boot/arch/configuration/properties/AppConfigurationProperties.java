package io.github.tuannh982.spring.boot.arch.configuration.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app")
@Data
public class AppConfigurationProperties {
    @Value("${app.locales}")
    private String[] locales;
}
