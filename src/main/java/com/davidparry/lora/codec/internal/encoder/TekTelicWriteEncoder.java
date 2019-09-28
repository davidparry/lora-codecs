package com.davidparry.lora.codec.internal.encoder;

import java.util.Base64;

public final class TekTelicWriteEncoder implements WriteEncoder<Byte> {
    public static final byte MOISTURE_ENABLED = (byte) 0xDC;


    @Override
    public String encode(Byte address, int value) {
        StringBuffer result = new StringBuffer();
        byte[] bytes = new byte[2];
        if (value <= 1) {
            bytes[0] = address;
            bytes[1] = (byte) value;
        }
        result.append(Base64.getEncoder().encodeToString(bytes));
        return result.toString();
    }

}
