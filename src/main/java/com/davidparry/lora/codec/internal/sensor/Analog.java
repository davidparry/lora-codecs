package com.davidparry.lora.codec.internal.sensor;

import com.davidparry.lora.codec.CayenneType;
import com.davidparry.lora.codec.Sensor;

import java.util.Objects;
import java.util.StringJoiner;

public class Analog extends BaseSensor implements Sensor<Double> {
    private Double value;

    public Analog(CayenneType type, Double value) {
        super(type);
        this.value = value;
    }

    @Override
    public Double getValue() {
        return this.value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Analog)) return false;
        Analog analog = (Analog) o;
        return Objects.equals(value, analog.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Analog.class.getSimpleName() + "[", "]")
                .add("value=" + value)
                .toString();
    }
}
