package com.davidparry.lora.codec;

public interface Sensor<T> {

    CayenneType getDataType();

    T getValue();
}
