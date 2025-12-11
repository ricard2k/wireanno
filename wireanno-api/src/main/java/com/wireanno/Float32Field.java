package com.wireanno;


import java.lang.annotation.*;

/**
 * Marks a getter method as a 32-bit floating point wire protocol field.
 * 
 * <p>This annotation defines a 4-byte IEEE 754 single-precision floating point field
 * in a wire protocol message. The getter method should return a {@code float} value
 * that will be serialized directly using {@link java.nio.ByteBuffer#putFloat(float)}.
 * 
 * <p><strong>Serialization:</strong>
 * <ul>
 *   <li>4 bytes, using IEEE 754 single-precision format</li>
 *   <li>Value range: approximately ±1.4E-45 to ±3.4E+38</li>
 *   <li>Precision: ~7 decimal digits</li>
 *   <li>Respects the specified endianness</li>
 * </ul>
 * 
 * <p><strong>Example:</strong>
 * <pre>
 * {@code
 * @Message
 * public interface SensorData {
 *     @Float32Field(fieldNum = 1, endian = Endian.BIG)
 *     float getTemperature();  // Temperature in Celsius
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
public @interface Float32Field {
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
