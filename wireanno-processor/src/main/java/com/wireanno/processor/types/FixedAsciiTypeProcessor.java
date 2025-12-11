package com.wireanno.processor.types;

import java.nio.charset.Charset;

import javax.lang.model.element.VariableElement;

import com.wireanno.Endian;
import com.wireanno.FixedAsciiField;

public class FixedAsciiTypeProcessor implements TypeProcessor<FixedAsciiField> {

    @Override
    public void getFieldVal(StringBuffer sb, FixedAsciiField anno, VariableElement field, Endian defaultEndian) {
        String name = field.getSimpleName().toString();
        Charset charset = Charset.forName(anno.charset());
        if (charset.newEncoder().maxBytesPerChar() != 1) {
            throw new IllegalArgumentException("Only single-byte character encodings are supported for FixedAsciiField: " + anno.charset());
        }
        sb.append("    byte[] raw_").append(name).append(" = instance.").append(name)
            .append("() == null ? new byte[0] : instance.").append(name)
            .append("().getBytes(StandardCharsets.").append(charset.name()).append(");\n");
        sb.append("    if (raw_").append(name).append(".length > ").append(anno.length())
            .append(") throw new IllegalArgumentException(\"").append(name)
            .append(" too long\");\n");
        sb.append("    buf.put(raw_").append(name).append(");\n");
        sb.append("    for (int i = raw_").append(name).append(".length; i < ")
            .append(anno.length()).append("; i++) buf.put((byte)").append((int) anno.pad()).append(");\n");
    }

    @Override
    public void getReadExpression(StringBuffer sb, FixedAsciiField anno, VariableElement field, Endian defaultEndian) {
        String name = field.getSimpleName().toString();
        Charset charset = Charset.forName(anno.charset());
        if (charset.newEncoder().maxBytesPerChar() != 1) {
            throw new IllegalArgumentException("Only single-byte character encodings are supported for FixedAsciiField: " + anno.charset());
        }
        sb.append("    byte[] d_").append(name).append(" = new byte[").append(anno.length()).append("];\n");
        sb.append("    buf.get(d_").append(name).append(");\n");
        sb.append("    int end_").append(name).append(" = d_").append(name).append(".length;\n");
        sb.append("    while (end_").append(name).append(" > 0 && d_").append(name)
            .append("[end_").append(name).append(" - 1] == (byte)").append((int) anno.pad()).append(") end_")
            .append(name).append("--;\n");
        sb.append("    out.put(\"").append(name).append("\", new String(d_").append(name)
            .append(", 0, end_").append(name).append(", StandardCharsets.").append(charset.name()).append("));\n");
    }
    
}