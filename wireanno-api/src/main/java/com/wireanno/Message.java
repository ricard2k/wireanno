package com.wireanno;

import java.lang.annotation.*;

@Retention(RetentionPolicy.CLASS)   // visible to processor; no runtime needed
@Target(ElementType.TYPE)
public @interface Message {
    Endian endian() default Endian.BIG;
}
