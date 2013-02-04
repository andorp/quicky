package org.andorp.quicky.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Target(java.lang.annotation.ElementType.METHOD)
public @interface Property {
    String name() default "";
    String description() default "";
    int runs() default 0;
    boolean ignore() default false;
}
