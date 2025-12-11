package com.wireanno;

/**
 * Specifies the byte order for wire protocol fields.
 * 
 * <p>Byte order (endianness) determines how multi-byte values are represented in the wire protocol:
 * <ul>
 *   <li><strong>BIG:</strong> Big-endian (network byte order). The most significant byte comes first.
 *       This is the default and is widely used in network protocols.</li>
 *   <li><strong>LITTLE:</strong> Little-endian (Intel byte order). The least significant byte comes first.
 *       Commonly used in x86/x64 architectures.</li>
 * </ul>
 * 
 * <p>Example:
 * The value {@code 0x12345678} is encoded as:
 * <ul>
 *   <li>BIG: {@code [0x12, 0x34, 0x56, 0x78]}</li>
 *   <li>LITTLE: {@code [0x78, 0x56, 0x34, 0x12]}</li>
 * </ul>
 * 
 * @since 0.1.0
 * @see Message#endian()
 * @see UInt16Field#endian()
 * @see UInt32Field#endian()
 * @see Float32Field#endian()
 */
public enum Endian {BIG, LITTLE}