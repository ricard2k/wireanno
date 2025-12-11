package com.wireanno.test;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Map;
import java.nio.charset.StandardCharsets;

/**
 * Tests for FixedAsciiField serialization and deserialization.
 */
public class FixedAsciiFieldTest {
    
    @Test
    public void testFixedAsciiEncode() throws Exception {
        TestMessageImpl msg = new TestMessageImpl();
        msg.hostname = "localhost";
        
        byte[] encoded = TestMessageSerializer.encode(msg);
        
        // FixedAsciiField is 16 bytes, starts after version(2) + sequence(4) + temperature(4)
        // at offset 10
        String result = new String(encoded, 10, 16, StandardCharsets.US_ASCII).trim();
        assertEquals("localhost", result);
    }
    
    @Test
    public void testFixedAsciiPadding() throws Exception {
        TestMessageImpl msg = new TestMessageImpl();
        msg.hostname = "test";  // Only 4 chars, will be padded with spaces
        
        byte[] encoded = TestMessageSerializer.encode(msg);
        
        // Check that padding is spaces
        for (int i = 14; i < 26; i++) {
            assertEquals(' ', (char) encoded[i]);
        }
    }
    
    @Test
    public void testFixedAsciiDecode() throws Exception {
        byte[] data = new byte[26];
        String hostname = "myserver";
        byte[] hostnameBytes = hostname.getBytes(StandardCharsets.US_ASCII);
        System.arraycopy(hostnameBytes, 0, data, 10, hostnameBytes.length);
        // Pad with spaces
        for (int i = 10 + hostnameBytes.length; i < 26; i++) {
            data[i] = ' ';
        }
        
        Map<String, Object> decoded = TestMessageSerializer.decode(data);
        
        String decodedHostname = (String) decoded.get("getHostname");
        assertEquals("myserver", decodedHostname);
    }
    
    @Test
    public void testFixedAsciiMaxLength() throws Exception {
        TestMessageImpl msg = new TestMessageImpl();
        msg.hostname = "1234567890ABCDEF";  // Exactly 16 chars
        
        byte[] encoded = TestMessageSerializer.encode(msg);
        Map<String, Object> decoded = TestMessageSerializer.decode(encoded);
        
        String decodedHostname = (String) decoded.get("getHostname");
        assertEquals("1234567890ABCDEF", decodedHostname);
    }
}
