package com.davidparry.lora.codec.internal.encoder;

import java.util.Base64;

public final class TekTelicReadEncoder implements ReadEncoder<Byte> {
    public static final byte DEVICE_EUI = 0; //0x00
    public static final byte TICK_SECONDS = 32; //0x20
    public static final byte TICK_BATTERY = 33; //0x21
    public static final byte TICK_TEMPERATURE = 34; //0x22
    public static final byte TICK_RHUMIDITY = 35; //0x23
    public static final byte TICK_REED_SWITCH = 36; //0x24
    public static final byte TICK_LIGHT = 37; //0x25
    public static final byte TICK_ACCELEROMETER = 38; //0x26
    public static final byte TICK_MCU_TEMP = 39; //0x27
    public static final byte TICK_MOISTURE = 40; //0x28
    public static final byte TICK_ANALOG_INPUT = 41; // 0x29
    public static final byte MOISTURE_SAMPLE_PERIOD = 90; //0x5A
    public static final byte MOISTURE_THRESHOLD = 91; //0x5B
    public static final byte MOISTURE_ENABLED = 92; //0x5C

    @Override
    public String encode(Byte... address) {
        StringBuffer result = new StringBuffer();
        if (address != null) {
            byte[] bytes = new byte[address.length];
            for (int i = 0; i < address.length; i++) {
                bytes[i] = address[i];
            }
            result.append(Base64.getEncoder().encodeToString(bytes));

        }
        return result.toString();
    }

}
