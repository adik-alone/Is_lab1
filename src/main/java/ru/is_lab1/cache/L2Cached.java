package ru.is_lab1.cache;

import jakarta.interceptor.InterceptorBinding;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.lang.annotation.ElementType.METHOD;

@InterceptorBinding
@Retention(RUNTIME)
@Target({TYPE, METHOD })
public @interface L2Cached {
    String key() default "";
}
