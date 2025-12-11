package com.wireanno.processor.types;

public class EndianUtil {

    /**
     * Generates code for little-endian encoding.
     * Creates a temporary ByteBuffer with LITTLE_ENDIAN byte order, writes the value,
     * and copies the bytes to the main buffer.
     *
     * @param sb The StringBuffer to append code to
     * @param fieldName The name of the field
     * @param byteLength The size in bytes (2 for short, 4 for int/float, etc.)
     * @param valueExpression The expression to get the value (e.g., "instance.myField()")
     * @param putMethod The ByteBuffer put method name (e.g., "putShort", "putInt", "putFloat")
     */
    public static void generateLittleEndianEncode(StringBuffer sb, String fieldName, int byteLength,
                                                   String valueExpression, String putMethod) {
        sb.append("    byte[] tmp_").append(fieldName).append(" = new byte[").append(byteLength).append("];\n");
        sb.append("    java.nio.ByteBuffer tmpBuf_").append(fieldName)
            .append(" = java.nio.ByteBuffer.wrap(tmp_").append(fieldName)
            .append(").order(java.nio.ByteOrder.LITTLE_ENDIAN);\n");
        sb.append("    tmpBuf_").append(fieldName).append(".").append(putMethod).append("(").append(valueExpression).append(");\n");
        sb.append("    buf.put(tmp_").append(fieldName).append(");\n");
    }

    /**
     * Generates code for little-endian decoding.
     * Reads bytes into a temporary array, wraps it in a ByteBuffer with LITTLE_ENDIAN byte order,
     * and reads the value.
     *
     * @param sb The StringBuffer to append code to
     * @param fieldName The name of the field
     * @param byteLength The size in bytes (2 for short, 4 for int/float, etc.)
     * @param getMethod The ByteBuffer get method name (e.g., "getShort", "getInt", "getFloat")
     * @param maskExpression Optional mask expression (e.g., " & 0xFFFF"), can be empty string
     */
    public static void generateLittleEndianDecode(StringBuffer sb, String fieldName, int byteLength,
                                                  String getMethod, String maskExpression) {
        sb.append("    byte[] tmp_").append(fieldName).append(" = new byte[").append(byteLength).append("];\n");
        sb.append("    buf.get(tmp_").append(fieldName).append(");\n");
        sb.append("    java.nio.ByteBuffer tmpBuf_").append(fieldName)
            .append(" = java.nio.ByteBuffer.wrap(tmp_").append(fieldName)
            .append(").order(java.nio.ByteOrder.LITTLE_ENDIAN);\n");
        sb.append("    out.put(\"").append(fieldName).append("\", tmpBuf_").append(fieldName)
            .append(".").append(getMethod).append("()").append(maskExpression).append(");\n");
    }
}
