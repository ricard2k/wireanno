+++
date = '2025-12-10T12:16:33Z'
draft = false
title = 'Getting Started with WireAnno'
description = 'Learn how to get started with WireAnno'
tags = ['tutorial', 'setup']
+++

## Introduction

WireAnno is a Java annotation processing framework designed for wire protocol code generation and validation.

## Installation

Add the WireAnno API to your project dependencies:

```gradle
dependencies {
    implementation 'com.wireanno:wireanno-api:1.0.0'
    annotationProcessor 'com.wireanno:wireanno-processor:1.0.0'
}
```

## Basic Usage

Create a wire protocol message using annotations:

```java
@Message
public class MyMessage {
    @UInt16(fieldNum=1)
    public int id;
    
    @FixedAsciiS(fieldNum=2, lenght=12)
    public String name;
}
```

## Next Steps

- Check out the [GitHub repository](https://github.com/ricard2k/wireanno)
- Read the full documentation
- Explore examples
