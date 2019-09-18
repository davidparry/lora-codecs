package com.davidparry.lora.codec.internal.sensor;

import com.davidparry.lora.codec.CayenneType;

import java.util.Objects;

public class BaseSensor {
    private CayenneType dataType;

    public BaseSensor(CayenneType dataType) {
        this.dataType = dataType;
    }

    public CayenneType getDataType() {
        return dataType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseSensor)) return false;
        BaseSensor that = (BaseSensor) o;
        return Objects.equals(dataType, that.dataType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataType);
    }
}
