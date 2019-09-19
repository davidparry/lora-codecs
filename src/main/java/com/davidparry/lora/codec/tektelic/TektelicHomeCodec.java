package com.davidparry.lora.codec.tektelic;

import com.davidparry.lora.codec.*;
import com.davidparry.lora.codec.internal.PayloadReader;
import com.davidparry.lora.codec.internal.PayloadResult;
import com.davidparry.lora.codec.internal.decoder.Decoder;

public class TektelicHomeCodec implements Codec<byte[]> {
    private Register register;
    private PayloadReader<Byte, byte[]> payloadReader;

    public TektelicHomeCodec(Register register, PayloadReader<Byte, byte[]> payloadReader) {
        this.register = register;
        this.payloadReader = payloadReader;
    }

    @Override
    public Payload decode(byte[] payload) {
        PayloadResult.Builder builder = PayloadResult.newBuilder();
        payloadReader.load(payload);
        while (payloadReader.isProcessing()) {
            byte channel = payloadReader.read();
            CayenneType type = register.dataType(channel);
            Decoder<Sensor, Byte, byte[]> decoder = register.decoder(channel);
            if (type != null && decoder != null) {
                builder.withSensor(decoder.decode(payloadReader, type));
            }
        }
        return builder.build();
    }

    @Override
    public String encode(Command command) {
        return null;
    }
}
