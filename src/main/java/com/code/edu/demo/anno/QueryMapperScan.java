package com.code.edu.demo.anno;

import com.code.edu.demo.registrar.QueryMapperScannerRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(QueryMapperScannerRegistrar.class)
public @interface QueryMapperScan {

    /**
     * Base packages to scan for MyBatis interfaces. Note that only interfaces
     * with at least one method will be registered; concrete classes will be
     * ignored.
     */
    String[] value() default {};
}
