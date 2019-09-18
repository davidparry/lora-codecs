package com.davidparry.lora.codec.internal.decoder;

import com.davidparry.lora.codec.CayenneType;
import com.davidparry.lora.codec.internal.PayloadReader;
import com.davidparry.lora.codec.internal.sensor.Accelerometer;

public class AccelerometerDecoder implements Decoder<Accelerometer, Byte, byte[]> {
    @Override
    public Accelerometer decode(PayloadReader<Byte, byte[]> payloadReader, CayenneType dataType) {
        return null;
    }
}
