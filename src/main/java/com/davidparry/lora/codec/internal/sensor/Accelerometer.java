package com.davidparry.lora.codec.internal.sensor;

import com.davidparry.lora.codec.CayenneType;
import com.davidparry.lora.codec.Sensor;

public class Accelerometer extends BaseSensor implements Sensor<AccelerometerData> {
    private AccelerometerData data;

    public Accelerometer(CayenneType dataType, AccelerometerData data) {
        super(dataType);
        this.data = data;
    }

    @Override
    public AccelerometerData getValue() {
        return this.data;
    }
}
