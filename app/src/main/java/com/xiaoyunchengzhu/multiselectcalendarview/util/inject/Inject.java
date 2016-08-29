package com.xiaoyunchengzhu.multiselectcalendarview.util.inject;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by zhangshiyu on 2016/6/2.
 */
@Retention(RUNTIME) @Target(FIELD)
 public @interface Inject {
    int[] value();


}