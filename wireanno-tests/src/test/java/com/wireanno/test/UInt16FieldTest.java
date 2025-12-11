package com.wireanno.test;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Map;

/**
 * Tests for UInt16Field serialization and deserialization.
 */
public class UInt16FieldTest {
    
    @Test
    public void testUInt16Encode() throws Exception {
        TestMessageImpl msg = new TestMessageImpl();
        msg.version = 0x1234;  // 4660 in decimal
        
        byte[] encoded = TestMessageSerializer.encode(msg);
        
        // Check first two bytes (UInt16 with value 0x1234 in big-endian)
        assertEquals(0x12, encoded[0] & 0xFF);
        assertEquals(0x34, encoded[1] & 0xFF);
    }
    
    @Test
    public void testUInt16Decode() throws Exception {
        byte[] data = new byte[26]; // Size of TestMessage
        data[0] = 0x12;
        data[1] = 0x34;
        
        Map<String, Object> decoded = TestMessageSerializer.decode(data);
        
        int version = ((Number) decoded.get("getVersion")).intValue();
        assertEquals(0x1234, version);
    }
    
    @Test
    public void testUInt16MaxValue() throws Exception {
        TestMessageImpl msg = new TestMessageImpl();
        msg.version = 0xFFFF;  // Max unsigned 16-bit
        
        byte[] encoded = TestMessageSerializer.encode(msg);
        Map<String, Object> decoded = TestMessageSerializer.decode(encoded);
        
        int version = ((Number) decoded.get("getVersion")).intValue();
        assertEquals(0xFFFF, version);
    }
    
    @Test
    public void testUInt16RoundTrip() throws Exception {
        TestMessageImpl msg = new TestMessageImpl();
        msg.version = 12345;
        
        byte[] encoded = TestMessageSerializer.encode(msg);
        Map<String, Object> decoded = TestMessageSerializer.decode(encoded);
        
        int version = ((Number) decoded.get("getVersion")).intValue();
        assertEquals(12345, version);
    }
}
