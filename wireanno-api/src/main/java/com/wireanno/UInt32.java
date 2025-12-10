package com.wireanno;


import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface UInt32 {
    int fieldNum();
    Endian endian() default Endian.BIG;
}
