package com.wireanno.processor.types;

import javax.lang.model.element.VariableElement;

import com.wireanno.Endian;

public interface TypeProcessor<T> {

    void getFieldVal(StringBuffer sb, T anno, VariableElement field, Endian defaultEndian);

    void getReadExpression(StringBuffer sb, T anno, VariableElement field, Endian defaultEndian);
    
}
