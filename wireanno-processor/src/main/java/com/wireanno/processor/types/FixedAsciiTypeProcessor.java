package com.wireanno.processor.types;

public class FixedAsciiTypeProcessor implements TypeProcessor {

    private final int length;

    public FixedAsciiTypeProcessor(int length) {
        this.length = length;
    }

    @Override
    public String getFieldVal(String varName) {
        return "new String(" + varName + ").trim()";
    }

    @Override
    public String getReadExpression(String varName) {
        return "new byte[" + length + "]";
    }
}