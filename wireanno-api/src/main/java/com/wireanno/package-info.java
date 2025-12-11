/**
 * WireAnno - Wire Protocol Annotation Framework.
 * 
 * <h2>Overview</h2>
 * This package provides annotations and utilities for defining wire protocol messages
 * with compile-time code generation. It uses Java annotation processing to generate
 * serialization and deserialization code for binary wire protocols.
 * 
 * <h2>Core Concepts</h2>
 * 
 * <h3>Message Definition</h3>
 * Define a wire protocol message using the {@link Message} annotation on an interface.
 * The interface should contain getter methods annotated with field type annotations
 * such as {@link UInt16Field}, {@link UInt32Field}, {@link Float32Field}, and
 * {@link FixedAsciiField}.
 * 
 * <h3>Field Ordering</h3>
 * Fields are serialized in ascending order of their {@code fieldNum} attribute.
 * This ensures consistent byte ordering across encoding and decoding.
 * 
 * <h3>Endianness</h3>
 * The {@link Endian} enum specifies byte order:
 * <ul>
 *   <li>{@code Endian.BIG} - Big-endian (network byte order), the default</li>
 *   <li>{@code Endian.LITTLE} - Little-endian (Intel byte order)</li>
 * </ul>
 * Endianness can be specified at the message level (applies to all fields) or
 * per-field for fine-grained control.
 * 
 * <h2>Supported Field Types</h2>
 * 
 * <ul>
 *   <li>{@link UInt16Field} - 16-bit unsigned integer (2 bytes)</li>
 *   <li>{@link UInt32Field} - 32-bit unsigned integer (4 bytes)</li>
 *   <li>{@link Float32Field} - 32-bit IEEE 754 floating point (4 bytes)</li>
 *   <li>{@link FixedAsciiField} - Fixed-length ASCII string with padding</li>
 * </ul>
 * 
 * <h2>Usage Example</h2>
 * 
 * <pre>
 * {@code
 * import com.wireanno.*;
 * 
 * @Message
 * public interface NetworkPacket {
 *     
 *     @UInt16Field(fieldNum = 1, endian = Endian.BIG)
 *     int getVersion();
 *     
 *     @UInt32Field(fieldNum = 2, endian = Endian.BIG)
 *     long getSequence();
 *     
 *     @FixedAsciiField(fieldNum = 3, length = 16, pad = ' ')
 *     String getHostname();
 *     
 *     @Float32Field(fieldNum = 4)
 *     float getTemperature();
 * }
 * 
 * // Generated code example:
 * // - NetworkPacketSerializer.encode(packet) -> byte[]
 * // - NetworkPacketSerializer.decode(bytes) -> Map<String, Object>
 * }
 * </pre>
 * 
 * <h2>Code Generation</h2>
 * 
 * The WireAnno processor generates serializer classes at compile time:
 * <ul>
 *   <li><strong>Encode Method</strong>: Converts message instances to byte arrays</li>
 *   <li><strong>Decode Method</strong>: Converts byte arrays back to field value maps</li>
 * </ul>
 * 
 * Generated serializers are placed in the same package as the message interface
 * with the suffix {@code Serializer} (e.g., {@code NetworkPacketSerializer}).
 * 
 * <h2>Compile-Time Processing</h2>
 * 
 * Ensure the WireAnno processor is in your annotation processor path during compilation.
 * The processor validates:
 * <ul>
 *   <li>Messages are defined on interfaces</li>
 *   <li>All field getters have unique field numbers</li>
 *   <li>Field types are supported</li>
 * </ul>
 * 
 * Compile-time errors are reported through the annotation processing framework.
 * 
 * <h2>Byte Layout</h2>
 * 
 * Messages are serialized as a sequence of typed fields in field number order:
 * <table style="border: 1px solid black;">
 *   <tr>
 *     <th>Field 1 Bytes</th>
 *     <th>Field 2 Bytes</th>
 *     <th>Field 3 Bytes</th>
 *     <th>...</th>
 *   </tr>
 * </table>
 * 
 * Each field respects its specified endianness independent of other fields.
 * 
 * @since 0.1.0
 * @see Message
 * @see Endian
 * @see UInt16Field
 * @see UInt32Field
 * @see Float32Field
 * @see FixedAsciiField
 */
package com.wireanno;

