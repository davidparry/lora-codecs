package com.davidparry.lora.codec.internal.encoder;

import java.util.Base64;

public final class TekTelicWriteEncoder implements WriteEncoder<Byte> {
    public static final byte MOISTURE_ENABLED = (byte) 0xDC;
    public static final byte MOISTURE_SAMPLE_PERIOD = (byte) 0xDA;
    public static final byte MOISTURE_THRESHOLD = (byte) 0xDB;
    public static final byte MOISTURE_CALIBRATE = (byte) 0xDD;
    public static final byte TICK_SECONDS = (byte) 0xA0;
    public static final byte TICK_BATTERY = (byte) 0xA1;
    public static final byte TICK_TEMPERATURE = (byte) 0xA2;
    public static final byte TICK_RHUMIDITY = (byte) 0xA3;
    public static final byte TICK_LIGHT = (byte) 0xA5;
    public static final byte TICK_ACCELEROMETER = (byte) 0xA6;
    public static final byte TICK_MCU_TEMP = (byte) 0xA7;
    public static final byte TICK_MOISTURE = (byte) 0xA8;
    public static final byte TICK_ANALOG_INPUT = (byte) 0xA9;


    @Override
    public String encode(Byte address, int value) {
        StringBuffer result = new StringBuffer();
        switch (address) {
            // 5 bytes 1 address 4 bytes number
            case TICK_SECONDS: {
                result.append(fourBytesShift(address, value));
                break;
            }
            // 3 bytes 1 address 2 bytes number
            case TICK_BATTERY:
            case TICK_TEMPERATURE:
            case TICK_RHUMIDITY:
            case TICK_LIGHT:
            case TICK_ACCELEROMETER:
            case TICK_MCU_TEMP:
            case TICK_MOISTURE:
            case TICK_ANALOG_INPUT: {
                result.append(twoBytesShift(address, value));
                break;
            }
            // 2 bytes 1 address 1 bytes number
            case MOISTURE_SAMPLE_PERIOD:
            case MOISTURE_THRESHOLD:
            case MOISTURE_CALIBRATE: {
                result.append(oneByteMask(address, value));
                break;
            }
            default: {
                result.append(enable(address));
                break;
            }
        }
        return result.toString();
    }

    private String enable(byte address) {
        byte[] bytes = new byte[2];
        bytes[0] = address;
        bytes[1] = (byte) 1;
        return Base64.getEncoder().encodeToString(bytes);
    }

    private String oneByteMask(byte address, int value) {
        byte[] toBase = {address, (byte) (value & 0xff)};
        return Base64.getEncoder().encodeToString(toBase);
    }

    private String fourBytesShift(byte address, int value) {
        byte[] toBase = {address, (byte) ((value >> 24) & 0xff), (byte) ((value >> 16) & 0xff)
                , (byte) ((value >> 8) & 0xff), (byte) (value & 0xff)};
        return Base64.getEncoder().encodeToString(toBase);
    }

    private String twoBytesShift(byte address, int value) {
        byte[] toBase = {address, (byte) ((value >> 8) & 0xff), (byte) (value & 0xff)};
        return Base64.getEncoder().encodeToString(toBase);
    }
}
