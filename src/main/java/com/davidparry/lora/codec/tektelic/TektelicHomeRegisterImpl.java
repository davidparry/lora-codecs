package com.davidparry.lora.codec.tektelic;

import com.davidparry.lora.codec.CayenneType;
import com.davidparry.lora.codec.internal.decoder.Decoder;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TektelicHomeRegisterImpl implements TektelicHomeRegister {
    private static final String NAME = "Tektlic Home Sensors version 2.0";
    private static Map<Byte, Decoder> decoders = new ConcurrentHashMap<>();
    private static Map<Byte, CayenneType> dataTypes = new ConcurrentHashMap<>();

    static {
        initDecoders();
        initDataTypes();
    }

    private static void initDataTypes() {
        dataTypes.put(BATTERY_VOLTAGE.getChannel(), BATTERY_VOLTAGE);
        dataTypes.put(MAGNETIC_SWITCH_COUNT.getChannel(), MAGNETIC_SWITCH_COUNT);
        dataTypes.put(ACCELEROMETER.getChannel(), ACCELEROMETER);
        dataTypes.put(TEMPERATURE.getChannel(), TEMPERATURE);
        dataTypes.put(EXTERNAL_INPUT_COUNT.getChannel(), EXTERNAL_INPUT_COUNT);
        dataTypes.put(EXTERNAL_INPUT.getChannel(), EXTERNAL_INPUT);
        dataTypes.put(MCU_TEMP.getChannel(), MCU_TEMP);
        dataTypes.put(MOISTURE.getChannel(), MOISTURE);
        dataTypes.put(HUMIDITY.getChannel(), HUMIDITY);
        dataTypes.put(LIGHT_DETECTION.getChannel(), LIGHT_DETECTION);
        dataTypes.put(MAGNETIC_SWITCH.getChannel(), MAGNETIC_SWITCH);
        dataTypes.put(SAMPLE_PERIOD.getChannel(), SAMPLE_PERIOD);
        dataTypes.put(MD_THRESHOLD.getChannel(), MD_THRESHOLD);
        dataTypes.put(MD_STATE.getChannel(), MD_STATE);
    }

    private static void initDecoders() {
        decoders.put(BATTERY_VOLTAGE.getChannel(), ANALOG_DECODER);
        decoders.put(MAGNETIC_SWITCH.getChannel(), DIGITAL_DECODER);
        decoders.put(LIGHT_DETECTION.getChannel(), DIGITAL_DECODER);
        decoders.put(TEMPERATURE.getChannel(), TEMP_DECODER);
        decoders.put(HUMIDITY.getChannel(), HUMIDITY_DECODER);
        decoders.put(ACCELEROMETER.getChannel(), ACCEL_DECODER);
        decoders.put(MAGNETIC_SWITCH_COUNT.getChannel(), COUNTER_DECODER);
        decoders.put(MOISTURE.getChannel(), DIGITAL_DECODER);
        decoders.put(MCU_TEMP.getChannel(), TEMP_DECODER);
        decoders.put(EXTERNAL_INPUT.getChannel(), DIGITAL_DECODER);
        decoders.put(EXTERNAL_INPUT_COUNT.getChannel(), COUNTER_DECODER);
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
