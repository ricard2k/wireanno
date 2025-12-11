package com.wireanno.test;

import com.wireanno.*;

/**
 * Test message with little-endian fields.
 */
@Message(endian = Endian.LITTLE)
public interface LittleEndianMessage {
    
    @UInt16Field(fieldNum = 1, endian = Endian.LITTLE)
    int getValue16();
    
    @UInt32Field(fieldNum = 2, endian = Endian.LITTLE)
    long getValue32();
    
    @Float32Field(fieldNum = 3, endian = Endian.LITTLE)
    float getFloatValue();
}
