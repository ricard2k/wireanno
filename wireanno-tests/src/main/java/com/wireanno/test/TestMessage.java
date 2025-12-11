package com.wireanno.test;

import com.wireanno.*;

/**
 * Test message with various field types.
 */
@Message(endian = Endian.BIG)
public interface TestMessage {
    
    @UInt16Field(fieldNum = 1, endian = Endian.BIG)
    int getVersion();
    
    @UInt32Field(fieldNum = 2, endian = Endian.BIG)
    long getSequence();
    
    @Float32Field(fieldNum = 3, endian = Endian.BIG)
    float getTemperature();
    
    @FixedAsciiField(fieldNum = 4, length = 16, pad = ' ')
    String getHostname();
}
