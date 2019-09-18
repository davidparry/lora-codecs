package com.davidparry.lora.codec.internal;

import com.davidparry.lora.codec.CayenneType;

public class ConvertUtil {

    public static int convertUnsignedByteArray(byte[] bytes) {
        if (bytes.length > 4) {
            throw new IllegalArgumentException(
                    "Invalid amount of bytes to convert to int: " + bytes);
        }
        int out = 0;
        for (int i = 0; i < bytes.length; i++) {
            out |= bytes[i] & 0xFF;
            if (i < bytes.length - 1) {
                out <<= 8;
            }
        }
        return out;
    }

    public static byte[] readBytes(CayenneType dataType, PayloadReader<Byte, byte[]> payloadReader) {
        int dataLength = dataType.getSize();
        byte[] data = new byte[dataLength];
        for (int i = 0; i < dataLength; i++) {
            data[i] = payloadReader.read();
        }
        return data;
    }

    public static int unsignedToInt(byte b) {
        return b & 0xFF;
    }

    public static byte hexToByte(String hexString) {
        int firstDigit = toDigit(hexString.charAt(0));
        int secondDigit = toDigit(hexString.charAt(1));
        return (byte) ((firstDigit << 4) + secondDigit);
    }

    private static int toDigit(char hexChar) {
        int digit = Character.digit(hexChar, 16);
        if (digit == -1) {
            throw new IllegalArgumentException(
                    "Invalid Hexadecimal Character: " + hexChar);
        }
        return digit;
    }


}
