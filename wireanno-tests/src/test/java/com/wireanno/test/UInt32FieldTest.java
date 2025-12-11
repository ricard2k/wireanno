package com.wireanno.test;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Map;

/**
 * Tests for UInt32Field serialization and deserialization.
 */
public class UInt32FieldTest {
    
    @Test
    public void testUInt32Encode() throws Exception {
        TestMessageImpl msg = new TestMessageImpl();
        msg.sequence = 0x12345678L;
        
        byte[] encoded = TestMessageSerializer.encode(msg);
        
        // Check bytes at offset 2-5 (UInt32 in big-endian)
        assertEquals(0x12, encoded[2] & 0xFF);
        assertEquals(0x34, encoded[3] & 0xFF);
        assertEquals(0x56, encoded[4] & 0xFF);
        assertEquals(0x78, encoded[5] & 0xFF);
    }
    
    @Test
    public void testUInt32Decode() throws Exception {
        byte[] data = new byte[26];
        data[2] = 0x12;
        data[3] = 0x34;
        data[4] = 0x56;
        data[5] = 0x78;
        
        Map<String, Object> decoded = TestMessageSerializer.decode(data);
        
        long sequence = ((Number) decoded.get("getSequence")).longValue();
        assertEquals(0x12345678L, sequence);
    }
    
    @Test
    public void testUInt32MaxValue() throws Exception {
        TestMessageImpl msg = new TestMessageImpl();
        msg.sequence = 0xFFFFFFFFL;
        
        byte[] encoded = TestMessageSerializer.encode(msg);
        Map<String, Object> decoded = TestMessageSerializer.decode(encoded);
        
        long sequence = ((Number) decoded.get("getSequence")).longValue();
        assertEquals(0xFFFFFFFFL, sequence);
    }
    
    @Test
    public void testUInt32RoundTrip() throws Exception {
        TestMessageImpl msg = new TestMessageImpl();
        msg.sequence = 123456789L;
        
        byte[] encoded = TestMessageSerializer.encode(msg);
        Map<String, Object> decoded = TestMessageSerializer.decode(encoded);
        
        long sequence = ((Number) decoded.get("getSequence")).longValue();
        assertEquals(123456789L, sequence);
    }
}
