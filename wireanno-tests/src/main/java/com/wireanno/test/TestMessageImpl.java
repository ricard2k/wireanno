package com.wireanno.test;

import com.wireanno.*;

/**
 * Implementation class for TestMessage interface.
 * Used for testing purposes.
 */
public class TestMessageImpl implements TestMessage {
    public int version;
    public long sequence;
    public float temperature;
    public String hostname;
    
    @Override
    @UInt16Field(fieldNum = 1, endian = Endian.BIG)
    public int getVersion() {
        return version;
    }
    
    @Override
    @UInt32Field(fieldNum = 2, endian = Endian.BIG)
    public long getSequence() {
        return sequence;
    }
    
    @Override
    @Float32Field(fieldNum = 3, endian = Endian.BIG)
    public float getTemperature() {
        return temperature;
    }
    
    @Override
    @FixedAsciiField(fieldNum = 4, length = 16, pad = ' ')
    public String getHostname() {
        return hostname;
    }
}
