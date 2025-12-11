package com.wireanno.test;

import com.wireanno.*;

/**
 * Implementation class for MixedEndianMessage interface.
 * Used for testing purposes.
 */
public class MixedEndianMessageImpl implements MixedEndianMessage {
    public int bigEndian16;
    public long littleEndian32;
    public String label;
    
    @Override
    @UInt16Field(fieldNum = 1, endian = Endian.BIG)
    public int getBigEndian16() {
        return bigEndian16;
    }
    
    @Override
    @UInt32Field(fieldNum = 2, endian = Endian.LITTLE)
    public long getLittleEndian32() {
        return littleEndian32;
    }
    
    @Override
    @FixedAsciiField(fieldNum = 3, length = 8, pad = '\0')
    public String getLabel() {
        return label;
    }
}
