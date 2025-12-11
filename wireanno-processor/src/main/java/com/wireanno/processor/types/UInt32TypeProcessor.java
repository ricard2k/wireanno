package com.wireanno.processor.types;

import javax.lang.model.element.VariableElement;

import com.wireanno.Endian;
import com.wireanno.UInt32Field;

public class UInt32TypeProcessor implements TypeProcessor<UInt32Field> {

    private static final int BYTE_LENGTH = 4;

    @Override
    public void getFieldVal(StringBuffer sb, UInt32Field anno, VariableElement field, Endian defaultEndian) {
        String name = field.getSimpleName().toString();
        String valueExpr = "(int)(instance." + name + "() & 0xFFFF_FFFFL)";
        Endian endian = anno.endian() != null ? anno.endian() : defaultEndian;
        
        if (endian == Endian.LITTLE) {
            EndianUtil.generateLittleEndianEncode(sb, name, BYTE_LENGTH, valueExpr, "putInt");
        } else {
            sb.append("    buf.putInt(").append(valueExpr).append(");\n");
        }
    }

    @Override
    public void getReadExpression(StringBuffer sb, UInt32Field anno, VariableElement field, Endian defaultEndian) {
        String name = field.getSimpleName().toString();
        Endian endian = anno.endian() != null ? anno.endian() : defaultEndian;
        
        if (endian == Endian.LITTLE) {
            EndianUtil.generateLittleEndianDecode(sb, name, BYTE_LENGTH, "getInt", " & 0xFFFF_FFFFL");
        } else {
            sb.append("    out.put(\"").append(name).append("\", buf.getInt() & 0xFFFF_FFFFL);\n");
        }
    }
    
}