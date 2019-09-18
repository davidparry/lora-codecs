package com.davidparry.lora.codec.internal.sensor;

import com.davidparry.lora.codec.CayenneType;
import com.davidparry.lora.codec.Sensor;

import java.util.Objects;

public class Humidity extends BaseSensor implements Sensor<Double> {
    private Double value;

    public Humidity(CayenneType dataType, Double value) {
        super(dataType);
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Humidity humidity = (Humidity) o;
        return Objects.equals(value, humidity.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public Double getValue() {
        return this.value;
    }
}
