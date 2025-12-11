package com.wireanno;

import java.lang.annotation.*;

/**
 * Marks a getter method as a 16-bit unsigned integer wire protocol field.
 * 
 * <p>This annotation defines a 2-byte unsigned integer field in a wire protocol message.
 * The getter method should return an {@code int} value that will be cast to {@code short}
 * and masked to {@code 0xFFFF} for unsigned interpretation.
 * 
 * <p><strong>Serialization:</strong>
 * <ul>
 *   <li>2 bytes, using {@link java.nio.ByteBuffer#putShort(short)}</li>
 *   <li>Value range: 0 to 65,535 (0xFFFF)</li>
 *   <li>Masked with {@code 0xFFFF} for unsigned semantics</li>
 * </ul>
 * 
 * <p><strong>Example:</strong>
 * <pre>
 * {@code
 * @Message
 * public interface Header {
 *     @UInt16Field(fieldNum = 1, endian = Endian.BIG)
 *     int getVersion();  // Can hold values 0-65535
 * }
 * }
 * </pre>
 * 
 * @since 01.0
 * @see Message
 * @see Endian
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface UInt16Field {
    /**
     * The field number, determining serialization order.
     * Fields are serialized in ascending order of {@code fieldNum}.
     * Must be unique within a message.
     * 
     * @return the field number (must be positive)
     */
    int fieldNum();
    
    /**
     * Byte order for this field.
     * Overrides the message-level endian setting if specified.
     * 
     * @return the byte order, defaults to {@link Message#endian()}
     */
    Endian endian();
}
