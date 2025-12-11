package com.wireanno;

import java.lang.annotation.*;

/**
 * Marks an interface as a wire protocol message definition.
 * 
 * <p>This annotation indicates that the interface defines a wire protocol message
 * that should have serialization/deserialization code generated at compile time.
 * The interface should contain getter methods annotated with field type annotations
 * such as {@link UInt16Field}, {@link UInt32Field}, {@link Float32Field}, and
 * {@link FixedAsciiField}.
 * 
 * <p><strong>Requirements:</strong>
 * <ul>
 *   <li>Must be applied to an interface</li>
 *   <li>Interface methods should be getters annotated with field annotations</li>
 *   <li>All field numbers must be unique within the message</li>
 *   <li>Fields are serialized in ascending order of their {@code fieldNum}</li>
 * </ul>
 * 
 * <p><strong>Generated Code:</strong>
 * For a message interface {@code MyMessage}, the processor generates:
 * <ul>
 *   <li>{@code MyMessageSerializer.encode(instance)} - Encodes to {@code byte[]}</li>
 *   <li>{@code MyMessageSerializer.decode(bytes)} - Decodes to {@code Map<String, Object>}</li>
 * </ul>
 * 
 * <p><strong>Example:</strong>
 * <pre>
 * {@code
 * @Message(endian = Endian.BIG)
 * public interface NetworkPacket {
 *     @UInt16Field(fieldNum = 1)
 *     int getVersion();
 *     
 *     @UInt32Field(fieldNum = 2)
 *     long getSequence();
 * }
 * }
 * </pre>
 * 
 * @since 0.1.0
 * @see Endian
 * @see UInt16Field
 * @see UInt32Field
 * @see Float32Field
 * @see FixedAsciiField
 */
@Retention(RetentionPolicy.CLASS)   // visible to processor; no runtime needed
@Target(ElementType.TYPE)
public @interface Message {
    /**
     * Default byte order for all fields in this message.
     * Individual fields can override this setting.
     * 
     * @return the byte order, defaults to {@link Endian#BIG}
     */
    Endian endian() default Endian.BIG;
}
