package com.davidparry.lora.codec.internal.decoder;

import com.davidparry.lora.codec.CayenneType;
import com.davidparry.lora.codec.internal.ConvertUtil;
import com.davidparry.lora.codec.internal.PayloadReader;
import com.davidparry.lora.codec.internal.sensor.Counter;

import java.util.logging.Logger;

public class CounterDecoder implements Decoder<Counter, Byte, byte[]> {
    private static final Logger LOG = Logger.getLogger(CounterDecoder.class.getName());
    private DataTypeValidator validator;

    public CounterDecoder(DataTypeValidator validator) {
        this.validator = validator;
    }

    @Override
    public Counter decode(PayloadReader<Byte, byte[]> payloadReader, CayenneType dataType) {
        validator.validate(payloadReader, dataType);
        LOG.fine("Payload present processing " + payloadReader.isProcessing());
        byte[] data = ConvertUtil.readBytes(dataType, payloadReader);
        int value = ConvertUtil.convertUnsignedByteArray(data);
        LOG.fine("Produced value " + value);
        return new Counter(dataType, value);
    }

}
