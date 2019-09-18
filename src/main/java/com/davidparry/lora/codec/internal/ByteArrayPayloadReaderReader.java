package com.davidparry.lora.codec.internal;

import java.util.Arrays;
import java.util.StringJoiner;

public class ByteArrayPayloadReaderReader implements PayloadReader<Byte, byte[]> {
    private byte[] payload;
    private int position = -1;

    public boolean isProcessing() {
        boolean flag = false;
        if (payload != null && payload.length > 0 && payload.length > position + 1) {
            flag = true;
        }
        return flag;
    }

    @Override
    public void load(byte[] data) {
        this.payload = data;
    }

    @Override
    public Byte read() {
        this.position++;
        Byte value = payload[position];
        return value;
    }

    public void rewind() {
        if (this.position > -1) {
            this.position--;
        }
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ByteArrayPayloadReaderReader
                .class.getSimpleName() + "[", "]")
                .add("payload=" + Arrays.toString(payload))
                .add("position=" + position)
                .toString();
    }
}
