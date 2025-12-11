# WireAnno Test Suite

This module contains comprehensive unit tests for the WireAnno framework.

## Test Structure

### Test Messages

The following test message interfaces are defined:

1. **TestMessage** - A standard message with all field types:
   - `getVersion()` - UInt16Field
   - `getSequence()` - UInt32Field  
   - `getTemperature()` - Float32Field
   - `getHostname()` - FixedAsciiField (16 bytes, padded with spaces)

2. **LittleEndianMessage** - A message for testing little-endian byte order:
   - `getValue16()` - UInt16Field with LITTLE endian
   - `getValue32()` - UInt32Field with LITTLE endian
   - `getFloatValue()` - Float32Field with LITTLE endian

3. **MixedEndianMessage** - A message with mixed endianness:
   - `getBigEndian16()` - UInt16Field with BIG endian
   - `getLittleEndian32()` - UInt32Field with LITTLE endian
   - `getLabel()` - FixedAsciiField (8 bytes, padded with null)

### Test Classes

#### UInt16FieldTest
Tests 16-bit unsigned integer field serialization and deserialization:
- `testUInt16Encode()` - Verify big-endian encoding
- `testUInt16Decode()` - Verify big-endian decoding
- `testUInt16MaxValue()` - Test maximum value (0xFFFF)
- `testUInt16RoundTrip()` - Test encode/decode cycle

#### UInt32FieldTest
Tests 32-bit unsigned integer field serialization and deserialization:
- `testUInt32Encode()` - Verify big-endian encoding
- `testUInt32Decode()` - Verify big-endian decoding
- `testUInt32MaxValue()` - Test maximum value (0xFFFF_FFFF)
- `testUInt32RoundTrip()` - Test encode/decode cycle

#### Float32FieldTest
Tests 32-bit floating-point field serialization and deserialization:
- `testFloat32Encode()` - Verify floating-point encoding
- `testFloat32RoundTrip()` - Test encode/decode cycle with precision
- `testFloat32NegativeValues()` - Test negative floating-point values
- `testFloat32LittleEndianRoundTrip()` - Test little-endian float handling

#### FixedAsciiFieldTest
Tests fixed-length ASCII string field serialization and deserialization:
- `testFixedAsciiEncode()` - Verify string encoding
- `testFixedAsciiPadding()` - Verify proper padding with spaces
- `testFixedAsciiDecode()` - Verify string decoding
- `testFixedAsciiMaxLength()` - Test full-length strings

#### EndianessTest
Tests message structure and basic round-trip functionality:
- `testBasicMessageStructure()` - Verify message size calculation
- `testRoundTripBasicMessage()` - Test full encode/decode cycle

## Running the Tests

Run all tests:
```bash
gradle build
```

Run only the test module:
```bash
gradle :wireanno-tests:test
```

Run a specific test class:
```bash
gradle :wireanno-tests:test --tests com.wireanno.test.UInt16FieldTest
```

Run a specific test method:
```bash
gradle :wireanno-tests:test --tests com.wireanno.test.UInt16FieldTest.testUInt16Encode
```

## Test Coverage

The test suite covers:
- ✅ Basic field type encoding (UInt16, UInt32, Float32, FixedAscii)
- ✅ Field decoding to Map<String, Object>
- ✅ Maximum value handling
- ✅ Round-trip serialization (encode -> decode)
- ✅ Floating-point precision
- ✅ String padding and trimming
- ✅ Multi-field message serialization

## Future Test Coverage

When per-field endianness support is fully integrated:
- [ ] Little-endian field encoding/decoding
- [ ] Mixed endianness within single message
- [ ] Endianness consistency across different architectures

## Test Dependencies

- JUnit 4.13.2 - Unit testing framework
- Java 8+ - Test execution environment
