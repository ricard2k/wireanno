package com.wireanno.processor.types;

import javax.lang.model.element.VariableElement;

import com.wireanno.Endian;
import com.wireanno.UInt16Field;

public class UInt16TypeProcessor implements TypeProcessor<UInt16Field> {

    private static final int BYTE_LENGTH = 2;

    @Override
    public void getFieldVal(StringBuffer sb, UInt16Field anno, VariableElement field, Endian defaultEndian) {
        String name = field.getSimpleName().toString();
        String valueExpr = "(short)(instance." + name + "() & 0xFFFF)";
        Endian endian = anno.endian() != null ? anno.endian() : defaultEndian;
        
        if (endian == Endian.LITTLE) {
            EndianUtil.generateLittleEndianEncode(sb, name, BYTE_LENGTH, valueExpr, "putShort");
        } else {
            sb.append("    buf.putShort(").append(valueExpr).append(");\n");
        }
    }

    @Override
    public void getReadExpression(StringBuffer sb, UInt16Field anno, VariableElement field, Endian defaultEndian) {
        String name = field.getSimpleName().toString();
        Endian endian = anno.endian() != null ? anno.endian() : defaultEndian;
        
        if (endian == Endian.LITTLE) {
            EndianUtil.generateLittleEndianDecode(sb, name, BYTE_LENGTH, "getShort", " & 0xFFFF");
        } else {
            sb.append("    out.put(\"").append(name).append("\", buf.getShort() & 0xFFFF);\n");
        }
    }
    
}