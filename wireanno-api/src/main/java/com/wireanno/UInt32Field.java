package com.wireanno;


import java.lang.annotation.*;

/**
 * Marks a getter method as a 32-bit unsigned integer wire protocol field.
 * 
 * <p>This annotation defines a 4-byte unsigned integer field in a wire protocol message.
 * The getter method should return a {@code long} value that will be cast to {@code int}
 * and masked to {@code 0xFFFF_FFFFL} for unsigned interpretation.
 * 
 * <p><strong>Serialization:</strong>
 * <ul>
 *   <li>4 bytes, using {@link java.nio.ByteBuffer#putInt(int)}</li>
 *   <li>Value range: 0 to 4,294,967,295 (0xFFFF_FFFF)</li>
 *   <li>Masked with {@code 0xFFFF_FFFFL} for unsigned semantics</li>
 * </ul>
 * 
 * <p><strong>Example:</strong>
 * <pre>
 * {@code
 * @Message
 * public interface NetworkPacket {
 *     @UInt32Field(fieldNum = 1, endian = Endian.LITTLE)
 *     long getSequenceNumber();  // Can hold values 0-4294967295
 * }
 * }
 * </pre>
 * 
 * @since 0.1.0
 * @see Message
 * @see Endian
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface UInt32Field {
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
