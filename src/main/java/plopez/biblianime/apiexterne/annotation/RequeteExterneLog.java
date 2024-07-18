package plopez.biblianime.apiexterne.annotation;

import plopez.biblianime.apiexterne.entity.ProviderExterne;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RequeteExterneLog {
    ProviderExterne provider();
}

