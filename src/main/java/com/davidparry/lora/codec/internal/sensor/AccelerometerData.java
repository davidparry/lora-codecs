package com.davidparry.lora.codec.internal.sensor;

public final class AccelerometerData {

    private float x;

    private float y;

    private float z;

    private AccelerometerData(Builder builder) {
        x = builder.x;
        y = builder.y;
        z = builder.z;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(AccelerometerData copy) {
        Builder builder = new Builder();
        builder.x = copy.getX();
        builder.y = copy.getY();
        builder.z = copy.getZ();
        return builder;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }


    @SuppressWarnings("PMD.AvoidFieldNameMatchingMethodName")
    public static final class Builder {
        private float x;
        private float y;
        private float z;

        private Builder() {
        }

        public Builder withX(float val) {
            x = val;
            return this;
        }

        public Builder withY(float val) {
            y = val;
            return this;
        }

        public Builder withZ(float val) {
            z = val;
            return this;
        }

        public AccelerometerData build() {
            return new AccelerometerData(this);
        }
    }
}
