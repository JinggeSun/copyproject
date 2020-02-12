package com.sun.mini.web.mvc;

import java.lang.annotation.*;

/**
 * @author zcm
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RequestMapping {
    String url();
}
