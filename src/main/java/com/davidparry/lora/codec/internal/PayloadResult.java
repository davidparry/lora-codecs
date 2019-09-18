package com.davidparry.lora.codec.internal;

import com.davidparry.lora.codec.Payload;
import com.davidparry.lora.codec.Sensor;

import java.util.ArrayList;
import java.util.List;

public final class PayloadResult implements Payload {

    private List<Sensor> sensors;

    private PayloadResult(Builder builder) {
        sensors = builder.sensors;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(PayloadResult copy) {
        Builder builder = new Builder();
        builder.sensors = copy.getSensors();
        return builder;
    }

    public List<Sensor> getSensors() {
        return sensors;
    }

    @SuppressWarnings("PMD.AvoidFieldNameMatchingMethodName")
    public static final class Builder {
        private List<Sensor> sensors = new ArrayList<>();

        private Builder() {
        }

        public Builder withSensor(Sensor sensor) {
            sensors.add(sensor);
            return this;
        }

        public Builder withSensors(List<Sensor> val) {
            sensors = val;
            return this;
        }

        public PayloadResult build() {
            return new PayloadResult(this);
        }
    }
}
