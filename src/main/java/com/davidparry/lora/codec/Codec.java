package com.davidparry.lora.codec;

public interface Codec<T> {

    Payload decode(T payload);

}
