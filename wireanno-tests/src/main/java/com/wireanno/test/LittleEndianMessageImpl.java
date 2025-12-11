package com.wireanno.test;

import com.wireanno.*;

/**
 * Implementation class for LittleEndianMessage interface.
 * Used for testing purposes.
 */
public class LittleEndianMessageImpl implements LittleEndianMessage {
    public int value16;
    public long value32;
    public float floatValue;
    
    @Override
    @UInt16Field(fieldNum = 1, endian = Endian.LITTLE)
    public int getValue16() {
        return value16;
    }
    
    @Override
    @UInt32Field(fieldNum = 2, endian = Endian.LITTLE)
    public long getValue32() {
        return value32;
    }
    
    @Override
    @Float32Field(fieldNum = 3, endian = Endian.LITTLE)
    public float getFloatValue() {
        return floatValue;
    }
}
