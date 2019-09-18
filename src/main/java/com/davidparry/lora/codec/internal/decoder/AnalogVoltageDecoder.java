package com.davidparry.lora.codec.internal.decoder;

import com.davidparry.lora.codec.CayenneType;
import com.davidparry.lora.codec.internal.ConvertUtil;
import com.davidparry.lora.codec.internal.PayloadReader;
import com.davidparry.lora.codec.internal.sensor.Analog;

import java.util.logging.Logger;

public class AnalogVoltageDecoder implements Decoder<Analog, Byte, byte[]> {
    private static final Logger LOG = Logger.getLogger(AnalogVoltageDecoder.class.getName());
    private DataTypeValidator validator;

    public AnalogVoltageDecoder(DataTypeValidator validator) {
        this.validator = validator;
    }

    @Override
    public Analog decode(PayloadReader<Byte, byte[]> payloadReader, CayenneType dataType) {
        validator.validate(payloadReader, dataType);
        LOG.fine("Payload present processing " + payloadReader.isProcessing());
        byte[] data = ConvertUtil.readBytes(dataType, payloadReader);
        int value = ConvertUtil.convertUnsignedByteArray(data);
        double result = (double) value / 100;
        LOG.fine("Produced value " + value);
        return new Analog(dataType, result);
    }
}
