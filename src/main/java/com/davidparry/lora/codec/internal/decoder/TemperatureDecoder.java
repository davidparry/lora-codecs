package com.davidparry.lora.codec.internal.decoder;

import com.davidparry.lora.codec.CayenneType;
import com.davidparry.lora.codec.internal.ConvertUtil;
import com.davidparry.lora.codec.internal.PayloadReader;
import com.davidparry.lora.codec.internal.sensor.Temperature;

import java.util.logging.Logger;

public class TemperatureDecoder implements Decoder<Temperature, Byte, byte[]> {
    private static final Logger LOG = Logger.getLogger(TemperatureDecoder.class.getName());
    private DataTypeValidator validator;

    public TemperatureDecoder(DataTypeValidator validator) {
        this.validator = validator;
    }

    @Override
    public Temperature decode(PayloadReader<Byte, byte[]> payloadReader, CayenneType dataType) {
        validator.validate(payloadReader, dataType);
        LOG.fine("Payload present processing " + payloadReader.isProcessing());
        byte[] data = ConvertUtil.readBytes(dataType, payloadReader);
        int value = ConvertUtil.convertUnsignedByteArray(data);
        double result = value * 0.1;
        LOG.fine("Processed  value " + result);
        return new Temperature(dataType, result);
    }

}
