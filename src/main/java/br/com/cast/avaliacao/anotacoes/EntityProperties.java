package br.com.cast.avaliacao.anotacoes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EntityProperties {

    String[] value() default {};

    String[] collecions() default {};

    Class targetEntity() default void.class;
}
