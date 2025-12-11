package com.wireanno;


import java.lang.annotation.*;

/**
 * Marks a getter method as a fixed-length ASCII string wire protocol field.
 * 
 * <p>This annotation defines a fixed-length ASCII string field in a wire protocol message.
 * The field always occupies a fixed number of bytes, padding or truncating as necessary.
 * The getter method should return a {@code String} value.
 * 
 * <p><strong>Serialization:</strong>
 * <ul>
 *   <li>Fixed byte length as specified by {@link #length()}</li>
 *   <li>Encoded using the specified charset (default: US_ASCII)</li>
 *   <li>Padded with {@link #pad()} character if shorter than {@code length}</li>
 *   <li>Truncated if longer than {@code length} (an error is thrown)</li>
 *   <li>Padding is stripped during deserialization</li>
 * </ul>
 * 
 * <p><strong>Example:</strong>
 * <pre>
 * {@code
 * @Message
 * public interface ServiceMessage {
 *     @FixedAsciiField(fieldNum = 1, length = 32, pad = ' ')
 *     String getServiceName();  // Always 32 bytes, padded with spaces
 * }
 * }
 * </pre>
 * 
 * @since 0.1.0
 * @see Message
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FixedAsciiField {
    /**
     * The field number, determining serialization order.
     * Fields are serialized in ascending order of {@code fieldNum}.
     * Must be unique within a message.
     * 
     * @return the field number (must be positive)
     */
    int fieldNum();
    
    /**
     * The fixed byte length of this field.
     * The field will always occupy exactly this many bytes in the wire format,
     * regardless of the actual string length.
     * 
     * @return the fixed length in bytes (must be positive)
     */
    int length();
    
    /**
     * The padding character used when the string is shorter than {@link #length()}.
     * During deserialization, trailing occurrences of this character are stripped.
     * 
     * @return the padding character, defaults to NUL ({@code '\0'})
     */
    char pad() default '\0';
    
    /**
     * The character set used for encoding/decoding the string.
     * Common values: "US_ASCII", "UTF-8", "ISO-8859-1"
     * 
     * @return the charset name, defaults to "US_ASCII"
     */
    String charset() default "US_ASCII";
}
