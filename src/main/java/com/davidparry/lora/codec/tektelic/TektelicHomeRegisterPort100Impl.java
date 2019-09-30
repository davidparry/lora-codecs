package com.davidparry.lora.codec.tektelic;

import com.davidparry.lora.codec.CayenneType;
import com.davidparry.lora.codec.internal.decoder.Decoder;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TektelicHomeRegisterPort100Impl implements TektelicHomeRegister {
    private static final String NAME = "Tektlic Home Sensors Port 100 version 2.0";
    private static Map<Byte, Decoder> decoders = new ConcurrentHashMap<>();
    private static Map<Byte, CayenneType> dataTypes = new ConcurrentHashMap<>();

    static {
        initDecoders();
        initDataTypes();
    }

    private static void initDataTypes() {
        dataTypes.put(SAMPLE_PERIOD.getChannel(), SAMPLE_PERIOD);
        dataTypes.put(MD_THRESHOLD.getChannel(), MD_THRESHOLD);
        dataTypes.put(MD_STATE.getChannel(), MD_STATE);
    }

    private static void initDecoders() {
        decoders.put(SAMPLE_PERIOD.getChannel(), MD_TRANS_DECODER);
        decoders.put(MD_THRESHOLD.getChannel(), MD_TRANS_DECODER);
        decoders.put(MD_STATE.getChannel(), MD_TRANS_DECODER);
    }

    public Decoder decoder(byte channel) {
        return decoders.get(channel);
    }

    public CayenneType dataType(byte channel) {
        return dataTypes.get(channel);
    }


    @Override
    public String name() {
        return NAME;
    }
}
