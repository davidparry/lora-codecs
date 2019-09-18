package com.davidparry.lora.codec.internal.sensor;

import com.davidparry.lora.codec.CayenneType;
import com.davidparry.lora.codec.Sensor;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * 1 for true and 0 for false
 * reported in hex as 0xFF truth 0x00 for false etc...
 */
public class Digital extends BaseSensor implements Sensor<Integer> {
    private Integer flag;

    /**
     * 1 true and 0 is false wire returned either a (0x00 false 0)  (0xFF true 1)
     *
     * @param dataType
     * @param flag
     */
    public Digital(CayenneType dataType, Integer flag) {
        super(dataType);
        this.flag = flag;
    }

    @Override
    public Integer getValue() {
        return this.flag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Digital digital = (Digital) o;
        return Objects.equals(flag, digital.flag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flag);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Digital.class.getSimpleName() + "[", "]")
                .add("flag=" + flag)
                .toString();
    }
}
