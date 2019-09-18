package com.davidparry.lora.codec.internal;

public interface PayloadReader<R, D> {

    R read();

    boolean isProcessing();

    void load(D data);

    void rewind();
}
