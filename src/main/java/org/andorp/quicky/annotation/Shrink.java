package org.andorp.quicky.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.andorp.quicky.IShrink;


@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Target(java.lang.annotation.ElementType.PARAMETER)
public @interface Shrink {
    Class<? extends IShrink<?>> value();
}
