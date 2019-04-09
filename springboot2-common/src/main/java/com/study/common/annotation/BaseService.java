package com.study.common.annotation;

import java.lang.annotation.*;

/**
 * 初始化继承BaseService的service
 * @author valiantzh
 * @version 1.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BaseService {
}
