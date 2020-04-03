package com.boommanpro.webfolderupload.bindenvironment.annotation;

import java.lang.annotation.*;

/**
 * @author boommanpro
 * @date 2020/3/4 1:21
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnableBindEnvironmentProperties {


    Class<?>[] value() default {};
}