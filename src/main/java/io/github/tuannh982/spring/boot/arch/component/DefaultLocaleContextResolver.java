package io.github.tuannh982.spring.boot.arch.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Slf4j
@Component
public class DefaultLocaleContextResolver extends AcceptHeaderLocaleResolver {
    public DefaultLocaleContextResolver(String[] locales) {
        List<Locale> lst = new ArrayList<>();
        for (String locale : locales) {
            lst.add(new Locale(locale));
        }
        if (!lst.isEmpty()) {
            this.setDefaultLocale(lst.get(0));
        }
        this.setSupportedLocales(lst);
    }
}
