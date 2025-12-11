package com.wireanno.processor.types;

public interface TypeProcessor<T> {

    String getFieldVal(T anno);

    String getReadExpression(T anno);
    
}
