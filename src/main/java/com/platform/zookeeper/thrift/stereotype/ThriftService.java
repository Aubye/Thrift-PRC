package com.platform.zookeeper.thrift.stereotype;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * The service Of thrift
 */

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface ThriftService {

    String name() default "";

}
