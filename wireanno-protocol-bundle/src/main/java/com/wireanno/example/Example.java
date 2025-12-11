package com.wireanno.example;

import com.wireanno.FixedAsciiField;
import com.wireanno.Message;
import com.wireanno.UInt16Field;

@Message
public interface Example {

    @UInt16Field(fieldNum = 1, endian = com.wireanno.Endian.BIG)
    public int id = 0;

    @FixedAsciiField(fieldNum = 2, length = 10, pad = '\0')
    public String name = "";
    
}
