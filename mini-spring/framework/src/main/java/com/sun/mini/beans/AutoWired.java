package com.sun.mini.beans;

import java.lang.annotation.*;

/**
 * @author zcm
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface AutoWired {
}
