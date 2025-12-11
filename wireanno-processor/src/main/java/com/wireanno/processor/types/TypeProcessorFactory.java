package com.wireanno.processor.types;

import java.lang.annotation.Annotation;

public class TypeProcessorFactory {

    public static Class<? extends TypeProcessor<?>> getTypeProcessor(Class<? extends Annotation> annoClass) {            
        if (annoClass.equals(com.wireanno.UInt16Field.class)) {
            return UInt16TypeProcessor.class;
        } else if (annoClass.equals(com.wireanno.UInt32Field.class)) {
            return UInt32TypeProcessor.class;
        } else if (annoClass.equals(com.wireanno.Float32Field.class)) {
            return Float32TypeProcessor.class;
        } else if (annoClass.equals(com.wireanno.FixedAsciiField.class)) {
            return FixedAsciiTypeProcessor.class;
        } else {
            throw new IllegalArgumentException("Unsupported annotation class: " + annoClass.getName());
        }
    }
}
