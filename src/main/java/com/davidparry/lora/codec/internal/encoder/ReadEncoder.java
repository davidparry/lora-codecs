package com.davidparry.lora.codec.internal.encoder;

public interface ReadEncoder<T> {

    String encode(T... address);

}
