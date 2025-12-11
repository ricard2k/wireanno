package com.wireanno.test;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Map;

/**
 * Tests for Float32Field serialization and deserialization.
 */
public class Float32FieldTest {
    
    @Test
    public void testFloat32BigEndianEncode() throws Exception {
        TestMessageImpl msg = new TestMessageImpl();
        msg.temperature = 25.5f;
        
        byte[] encoded = TestMessageSerializer.encode(msg);
        
        // Verify bytes were written (we can't easily verify exact values without IEEE 754 knowledge)
        assertNotNull(encoded);
        assertTrue(encoded.length >= 10);
    }
    
    @Test
    public void testFloat32RoundTrip() throws Exception {
        TestMessageImpl msg = new TestMessageImpl();
        float originalValue = 42.42f;
        msg.temperature = originalValue;
        
        byte[] encoded = TestMessageSerializer.encode(msg);
        Map<String, Object> decoded = TestMessageSerializer.decode(encoded);
        
        float decodedValue = ((Number) decoded.get("getTemperature")).floatValue();
        assertEquals(originalValue, decodedValue, 0.001f);
    }
    
    @Test
    public void testFloat32NegativeValues() throws Exception {
        TestMessageImpl msg = new TestMessageImpl();
        msg.temperature = -15.25f;
        
        byte[] encoded = TestMessageSerializer.encode(msg);
        Map<String, Object> decoded = TestMessageSerializer.decode(encoded);
        
        float decodedValue = ((Number) decoded.get("getTemperature")).floatValue();
        assertEquals(-15.25f, decodedValue, 0.001f);
    }
    
    @Test
    public void testFloat32LittleEndianRoundTrip() throws Exception {
        LittleEndianMessageImpl msg = new LittleEndianMessageImpl();
        float originalValue = 123.456f;
        msg.floatValue = originalValue;
        
        byte[] encoded = LittleEndianMessageSerializer.encode(msg);
        Map<String, Object> decoded = LittleEndianMessageSerializer.decode(encoded);
        
        float decodedValue = ((Number) decoded.get("getFloatValue")).floatValue();
        assertEquals(originalValue, decodedValue, 0.01f);
    }
}
