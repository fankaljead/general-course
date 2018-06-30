package cn.edu.cqut.util;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Column {

    // 是否为主键
    public boolean isId() default false;

    // 在表中的列名
    public String name() default "";

    // 传给前台的名称
    public String caption() default "";

    //是否可以为空
    public boolean nullable() default true;

    // 长度
    public int length() default 20;

    // 在数据库中的类型
    public String type() default "string";

}
