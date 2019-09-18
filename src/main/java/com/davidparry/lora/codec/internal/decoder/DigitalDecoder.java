package com.davidparry.lora.codec.internal.decoder;

import com.davidparry.lora.codec.CayenneType;
import com.davidparry.lora.codec.internal.PayloadReader;
import com.davidparry.lora.codec.internal.sensor.Digital;

import java.util.logging.Logger;

public class DigitalDecoder implements Decoder<Digital, Byte, byte[]> {
    private static final Logger LOG = Logger.getLogger(DigitalDecoder.class.getName());

    private DataTypeValidator validator;

    public DigitalDecoder(DataTypeValidator validator) {
        this.validator = validator;
    }

    @Override
    public Digital decode(PayloadReader<Byte, byte[]> payloadReader, CayenneType dataType) {
        validator.validate(payloadReader, dataType);
        LOG.fine("Payload present processing " + payloadReader.isProcessing());

        int value = 0;
        // 255 || 0xFF which we are giving value of 1 for true else keep false 0 which byte would be 0
        byte data = payloadReader.read();
        if (data == (byte) -1) {
            value = 1;
        }
        LOG.fine("Processed value " + value);
        return new Digital(dataType, value);

    }
}
