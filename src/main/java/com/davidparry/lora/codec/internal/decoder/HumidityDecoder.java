package com.davidparry.lora.codec.internal.decoder;

import com.davidparry.lora.codec.CayenneType;
import com.davidparry.lora.codec.internal.ConvertUtil;
import com.davidparry.lora.codec.internal.PayloadReader;
import com.davidparry.lora.codec.internal.sensor.Humidity;

import java.util.logging.Logger;

public class HumidityDecoder implements Decoder<Humidity, Byte, byte[]> {
    private static final Logger LOG = Logger.getLogger(HumidityDecoder.class.getName());

    private DataTypeValidator validator;

    public HumidityDecoder(DataTypeValidator validator) {
        this.validator = validator;
    }

    @Override
    public Humidity decode(PayloadReader<Byte, byte[]> payloadReader, CayenneType dataType) {
        validator.validate(payloadReader, dataType);
        LOG.fine("Payload present processing " + payloadReader.isProcessing());
        byte data = payloadReader.read();
        int number = ConvertUtil.unsignedToInt(data);
        double value = number * 0.5;
        LOG.fine("Processed value " + value);
        return new Humidity(dataType, value);
    }
}
