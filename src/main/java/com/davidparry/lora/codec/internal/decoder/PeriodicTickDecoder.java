package com.davidparry.lora.codec.internal.decoder;

import com.davidparry.lora.codec.CayenneType;
import com.davidparry.lora.codec.internal.ConvertUtil;
import com.davidparry.lora.codec.internal.PayloadReader;
import com.davidparry.lora.codec.internal.sensor.Digital;

import java.util.logging.Logger;

public class PeriodicTickDecoder implements Decoder<Digital, Byte, byte[]> {
    private static final Logger LOG = Logger.getLogger(PeriodicTickDecoder.class.getName());

    public PeriodicTickDecoder() {

    }

    @Override
    public Digital decode(PayloadReader<Byte, byte[]> payloadReader, CayenneType dataType) {
        LOG.fine("Payload present processing " + payloadReader.isProcessing());
        byte[] data = ConvertUtil.readBytes(dataType, payloadReader);
        int value = ConvertUtil.convertUnsignedByteArray(data);
        LOG.fine("Produced value " + value);
        return new Digital(dataType, value);
    }

}
