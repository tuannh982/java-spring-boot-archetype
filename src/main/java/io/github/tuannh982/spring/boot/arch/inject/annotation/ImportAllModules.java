package io.github.tuannh982.spring.boot.arch.inject.annotation;

import io.github.tuannh982.spring.boot.arch.inject.AllModules;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(AllModules.class)
@Documented
public @interface ImportAllModules {
}
