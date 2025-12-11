package com.wireanno.test;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Map;

/**
 * Tests for mixed endianness and multiple field types.
 * Note: Endianness support is in progress. These tests will pass when per-field
 * endianness support is fully implemented.
 */
public class EndianessTest {
    
    @Test
    public void testBasicMessageStructure() throws Exception {
        TestMessageImpl msg = new TestMessageImpl();
        msg.version = 0x1234;
        msg.sequence = 0x12345678L;
        msg.temperature = 25.0f;
        msg.hostname = "test";
        
        byte[] encoded = TestMessageSerializer.encode(msg);
        assertNotNull(encoded);
        assertEquals(26, encoded.length);  // 2 + 4 + 4 + 16 bytes
    }
    
    @Test
    public void testRoundTripBasicMessage() throws Exception {
        TestMessageImpl msg = new TestMessageImpl();
        msg.version = 255;
        msg.sequence = 12345L;
        msg.temperature = 20.5f;
        msg.hostname = "localhost";
        
        byte[] encoded = TestMessageSerializer.encode(msg);
        Map<String, Object> decoded = TestMessageSerializer.decode(encoded);
        
        assertNotNull(decoded);
        assertEquals(255, ((Number) decoded.get("getVersion")).intValue());
        assertEquals(12345L, ((Number) decoded.get("getSequence")).longValue());
        assertEquals("localhost", decoded.get("getHostname"));
    }
}
