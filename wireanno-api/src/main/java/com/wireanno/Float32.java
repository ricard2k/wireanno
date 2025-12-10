package com.wireanno;


import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Float32 {
    int fieldNum();
    Endian endian() default Endian.BIG;
}
