package com.davidparry.lora.codec.internal;

import java.util.Arrays;
import java.util.StringJoiner;

/**
 * This class is not meant to be ThreadSafe create new instances for each time you want to consume the Payload is
 * the preferred way.
 */
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
        this.position = -1;
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
