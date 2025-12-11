package com.wireanno.test;

import com.wireanno.*;

/**
 * Test message with mixed endianness.
 */
@Message(endian = Endian.BIG)
public interface MixedEndianMessage {
    
    @UInt16Field(fieldNum = 1, endian = Endian.BIG)
    int getBigEndian16();
    
    @UInt32Field(fieldNum = 2, endian = Endian.LITTLE)
    long getLittleEndian32();
    
    @FixedAsciiField(fieldNum = 3, length = 8, pad = '\0')
    String getLabel();
}
