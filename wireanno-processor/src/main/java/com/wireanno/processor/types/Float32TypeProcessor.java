package com.wireanno.processor.types;

import javax.lang.model.element.VariableElement;

import com.wireanno.Endian;
import com.wireanno.Float32Field;

public class Float32TypeProcessor implements TypeProcessor<Float32Field> {

    private static final int BYTE_LENGTH = 4;

    @Override
    public void getFieldVal(StringBuffer sb, Float32Field anno, VariableElement field, Endian defaultEndian) {

        Endian endian = anno.endian() != null ? anno.endian() : defaultEndian;

        String name = field.getSimpleName().toString();
        String valueExpr = "instance." + name + "()";
        
        if (endian == Endian.LITTLE) {
            EndianUtil.generateLittleEndianEncode(sb, name, BYTE_LENGTH, valueExpr, "putFloat");
        } else {
            sb.append("    buf.putFloat(").append(valueExpr).append(");\n");
        }
    }

    @Override
    public void getReadExpression(StringBuffer sb, Float32Field anno, VariableElement field, Endian defaultEndian) {
        String name = field.getSimpleName().toString();
        Endian endian = anno.endian() != null ? anno.endian() : defaultEndian;
        
        if (endian == Endian.LITTLE) {
            EndianUtil.generateLittleEndianDecode(sb, name, BYTE_LENGTH, "getFloat", "");
        } else {
            sb.append("    out.put(\"").append(name).append("\", buf.getFloat());\n");
        }
    }
    
}