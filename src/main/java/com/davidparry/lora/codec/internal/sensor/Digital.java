package com.davidparry.lora.codec.internal.sensor;

import com.davidparry.lora.codec.CayenneType;
import com.davidparry.lora.codec.Sensor;

import java.util.Objects;
import java.util.StringJoiner;

/**
 *
 */
public class Digital extends BaseSensor implements Sensor<Integer> {
    private Integer value;

    /**
     *
     * @param dataType
     * @param value
     */
    public Digital(CayenneType dataType, Integer value) {
        super(dataType);
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Digital digital = (Digital) o;
        return Objects.equals(value, digital.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Digital.class.getSimpleName() + "[", "]")
                .add("value=" + value)
                .toString();
    }
}
