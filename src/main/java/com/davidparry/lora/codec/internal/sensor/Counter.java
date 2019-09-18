package com.davidparry.lora.codec.internal.sensor;

import com.davidparry.lora.codec.CayenneType;
import com.davidparry.lora.codec.Sensor;

import java.util.Objects;
import java.util.StringJoiner;

public class Counter extends BaseSensor implements Sensor<Integer> {
    private Integer count;

    public Counter(CayenneType dataType, Integer count) {
        super(dataType);
        this.count = count;
    }

    @Override
    public Integer getValue() {
        return this.count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Counter)) return false;
        if (!super.equals(o)) return false;
        Counter counter = (Counter) o;
        return Objects.equals(count, counter.count);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), count);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Counter.class.getSimpleName() + "[", "]")
                .add("count=" + count)
                .toString();
    }
}
