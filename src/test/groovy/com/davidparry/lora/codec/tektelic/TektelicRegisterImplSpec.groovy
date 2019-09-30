package com.davidparry.lora.codec.tektelic

import com.davidparry.lora.codec.DataType
import com.davidparry.lora.codec.internal.decoder.Decoder
import spock.lang.Specification

import static com.davidparry.lora.codec.tektelic.TektelicHomeRegister.*

class TektelicRegisterImplSpec extends Specification {


    def setup() {
    }

    def "for #DT all the SensorTypes port10 byByte "() {

        when:
        TektelicHomeRegisterPort10Impl register = new TektelicHomeRegisterPort10Impl()
        DataType channel = register.dataType(DT.getChannel())
        Decoder decoder = register.decoder(DT.getChannel())

        then:
        channel == DT
        decoder == DC

        where:
        DT                    | DC
        ACCELEROMETER         | ACCEL_DECODER
        MOISTURE              | DIGITAL_DECODER
        MCU_TEMP              | TEMP_DECODER
        MAGNETIC_SWITCH       | DIGITAL_DECODER
        MAGNETIC_SWITCH_COUNT | COUNTER_DECODER
        LIGHT_DETECTION       | DIGITAL_DECODER
        HUMIDITY              | HUMIDITY_DECODER
        EXTERNAL_INPUT        | DIGITAL_DECODER
        EXTERNAL_INPUT_COUNT  | COUNTER_DECODER
        TEMPERATURE           | TEMP_DECODER
        BATTERY_VOLTAGE       | ANALOG_DECODER

    }

    def "for #DT all the SensorTypes port 100 byByte "() {

        when:
        TektelicHomeRegisterPort100Impl register = new TektelicHomeRegisterPort100Impl()
        DataType channel = register.dataType(DT.getChannel())
        Decoder decoder = register.decoder(DT.getChannel())

        then:
        channel == DT
        decoder == DC

        where:
        DT            | DC
        SAMPLE_PERIOD | MD_TRANS_DECODER
        MD_THRESHOLD  | MD_TRANS_DECODER
        MD_STATE | MD_TRANS_DECODER


    }


}


