package com.davidparry.lora.codec.internal.encoder;

public interface WriteEncoder<T> {

    String encode(T address, int value);

}
