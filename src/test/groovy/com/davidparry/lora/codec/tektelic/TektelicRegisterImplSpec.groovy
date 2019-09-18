package com.davidparry.lora.codec.tektelic


import com.davidparry.lora.codec.DataType
import com.davidparry.lora.codec.internal.decoder.Decoder
import spock.lang.Specification

import static TektelicHomeRegister.*

class TektelicRegisterImplSpec extends Specification {


    def setup() {
    }

    def "for #DT all the SensorTypes byByte "() {

        when:
        TektelicHomeRegisterImpl register = new TektelicHomeRegisterImpl()
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

}


