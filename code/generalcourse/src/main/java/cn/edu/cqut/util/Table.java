package cn.edu.cqut.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {

    // 数据库中的表名
    public String name() default "entity";

    // 返回前台的名称
    public String caption() default "entity";
}
