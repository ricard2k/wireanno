package com.wireanno;


import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface FixedAscii {
    int fieldNum();
    int length();                 // fixed byte length
    char pad() default '\0';      // pad char if shorter
}
