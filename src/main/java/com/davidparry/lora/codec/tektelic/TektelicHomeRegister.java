package com.davidparry.lora.codec.tektelic;

import com.davidparry.lora.codec.CayenneType;
import com.davidparry.lora.codec.DataType;
import com.davidparry.lora.codec.Register;
import com.davidparry.lora.codec.SensorKey;
import com.davidparry.lora.codec.internal.decoder.*;

public interface TektelicHomeRegister extends Register {

    CayenneType BATTERY_VOLTAGE = DataType.newBuilder()
            .withDeciPos(2)
            .withLlpX("FF")
            .withLpp((byte) 255)
            .withChannelX("00")
            .withChannel((byte) 0)
            .withKey(SensorKey.V)
            .withLabel("Battery Voltage")
            .withSigned(true)
            .withSize(2)
            .build();
    CayenneType MAGNETIC_SWITCH_COUNT = DataType.newBuilder()
            .withDeciPos(0)
            .withKey(SensorKey.MSC)
            .withLabel("Magnetic (Reed) Switch Count")
            .withLpp((byte) 4)
            .withLlpX("04")
            .withChannelX("08")
            .withChannel((byte) 8)
            .withSigned(true)
            .withSize(2)
            .build();
    CayenneType ACCELEROMETER = DataType.newBuilder()
            .withDeciPos(6)
            .withKey(SensorKey.ACCLRM)
            .withLabel("Accelerometer Data")
            .withLpp((byte) 113)
            .withLlpX("71")
            .withChannelX("07")
            .withChannel((byte) 7)
            .withSigned(true)
            .withSize(6)
            .build();
    CayenneType TEMPERATURE = DataType.newBuilder()
            .withDeciPos(1)
            .withKey(SensorKey.T)
            .withLabel("Temperature")
            .withLpp((byte) 103)
            .withLlpX("67")
            .withChannelX("03")
            .withChannel((byte) 3)
            .withSigned(true)
            .withSize(2)
            .build();
    CayenneType EXTERNAL_INPUT_COUNT = DataType.newBuilder()
            .withDeciPos(0)
            .withKey(SensorKey.ECC)
            .withLabel("External Connection (Input) Count")
            .withLpp((byte) 4)
            .withLlpX("04")
            .withChannelX("0F")
            .withChannel((byte) 15)
            .withSigned(true)
            .withSize(2)
            .build();
    CayenneType EXTERNAL_INPUT = DataType.newBuilder()
            .withDeciPos(0)
            .withKey(SensorKey.EC)
            .withLabel("External Connection (Input)")
            .withLpp((byte) 0)
            .withLlpX("00")
            .withChannelX("0E")
            .withChannel((byte) 14)
            .withSigned(true)
            .withSize(1)
            .build();
    CayenneType MCU_TEMP = DataType.newBuilder()
            .withDeciPos(1)
            .withKey(SensorKey.MCU_T)
            .withLabel("MCU Temperature")
            .withLpp((byte) 103)
            .withLlpX("67")
            .withChannelX("0B")
            .withChannel((byte) 11)
            .withSigned(true)
            .withSize(2)
            .build();
    CayenneType MOISTURE = DataType.newBuilder()
            .withDeciPos(0)
            .withKey(SensorKey.MD)
            .withLabel("Leak Detection / Moisture Detection")
            .withLpp((byte) 0)
            .withLlpX("00")
            .withChannelX("09")
            .withChannel((byte) 9)
            .withSigned(true)
            .withSize(1)
            .build();
    CayenneType HUMIDITY = DataType.newBuilder()
            .withDeciPos(1)
            .withKey(SensorKey.RH)
            .withLabel("Relative Humidity RH")
            .withLpp((byte) 104)
            .withLlpX("68")
            .withChannelX("04")
            .withChannel((byte) 4)
            .withSigned(false)
            .withSize(1)
            .build();
    CayenneType LIGHT_DETECTION = DataType.newBuilder()
            .withDeciPos(0)
            .withKey(SensorKey.LD)
            .withLabel("Light Detection")
            .withLpp((byte) 0)
            .withLlpX("00")
            .withChannelX("02")
            .withChannel((byte) 2)
            .withSigned(true)
            .withSize(1)
            .build();
    CayenneType MAGNETIC_SWITCH = DataType.newBuilder()
            .withDeciPos(0)
            .withKey(SensorKey.MS)
            .withLabel("Magnetic Switch / Reed Switch")
            .withLpp((byte) 0)
            .withLlpX("00")
            .withChannelX("01")
            .withChannel((byte) 1)
            .withSigned(true)
            .withSize(1)
            .build();
    CayenneType SAMPLE_PERIOD = DataType.newBuilder()
            .withDeciPos(0)
            .withKey(SensorKey.SP)
            .withLabel("Moisture Detection Sample Period, 1 = 16 sec , 2 = 32 sec, 3 = 64 sec , 4 = 128 sec")
            .withChannelX("5A")
            .withChannel((byte) 90)
            .withSigned(true)
            .withSize(1)
            .build();
    CayenneType MD_THRESHOLD = DataType.newBuilder()
            .withDeciPos(0)
            .withKey(SensorKey.THOLD)
            .withLabel("Moisture Detection Threshold: Nominally, a 1/4” of water below the Home Sensor " +
                    "results in a shift of about 300 units from the dry measurement baseline")
            .withChannelX("5B")
            .withChannel((byte) 91)
            .withSigned(true)
            .withSize(1)
            .build();
    CayenneType MD_STATE = DataType.newBuilder()
            .withDeciPos(0)
            .withKey(SensorKey.MD_STATE)
            .withLabel("Moisture Detection State 1 active 0 inactive")
            .withChannelX("5C")
            .withChannel((byte) 92)
            .withSigned(true)
            .withSize(1)
            .build();

    Decoder ANALOG_DECODER = new AnalogVoltageDecoder(new DataTypeByteValidator());
    Decoder ACCEL_DECODER = new AccelerometerDecoder();
    Decoder COUNTER_DECODER = new CounterDecoder(new DataTypeByteValidator());
    Decoder DIGITAL_DECODER = new DigitalDecoder(new DataTypeByteValidator());
    Decoder HUMIDITY_DECODER = new HumidityDecoder(new DataTypeByteValidator());
    Decoder TEMP_DECODER = new TemperatureDecoder(new DataTypeByteValidator());
    Decoder MD_TRANS_DECODER = new MoistureTransducerDecoder();
    /**
     * Name of this register used for display only
     *
     * @return name of register
     */
    String name();

}
