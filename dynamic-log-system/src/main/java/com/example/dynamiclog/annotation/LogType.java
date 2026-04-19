package com.example.dynamiclog.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogType {

    /**
     * Kullanıcının seçeceği tip anahtarı (örn. "db", "xml", "text").
     */
    String value();
}
