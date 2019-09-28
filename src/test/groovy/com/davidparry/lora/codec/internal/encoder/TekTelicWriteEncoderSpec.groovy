package com.davidparry.lora.codec.internal.encoder


import spock.lang.Specification

class TekTelicWriteEncoderSpec extends Specification {
    def setup() {

    }

    def "for the given #bytes enable or encode #value to turn to base64 #answer"() {
        given:
        TekTelicWriteEncoder encoder = new TekTelicWriteEncoder()

        when:
        String result = encoder.encode(bytes, value)

        then:
        result == answer

        where:
        bytes                                       | value  | answer
        TekTelicWriteEncoder.MOISTURE_ENABLED       | 1      | "3AE="
        TekTelicWriteEncoder.MOISTURE_ENABLED       | 0      | "3AE="
        TekTelicWriteEncoder.MOISTURE_ENABLED       | 234567 | "3AE="
        TekTelicWriteEncoder.TICK_SECONDS           | 9      | "oAAAAAk="
        TekTelicWriteEncoder.TICK_SECONDS           | 86400  | "oAABUYA="
        TekTelicWriteEncoder.TICK_SECONDS           | 1      | "oAAAAAE="
        TekTelicWriteEncoder.TICK_BATTERY           | 65535  | "of//"
        TekTelicWriteEncoder.TICK_BATTERY           | 1      | "oQAB"
        TekTelicWriteEncoder.TICK_BATTERY           | 12     | "oQAM"
        TekTelicWriteEncoder.MOISTURE_SAMPLE_PERIOD | 4      | "2gQ="
        TekTelicWriteEncoder.MOISTURE_THRESHOLD     | 255    | "2/8="
        TekTelicWriteEncoder.MOISTURE_THRESHOLD     | 50     | "2zI="
        TekTelicWriteEncoder.TICK_TEMPERATURE       | 45     | "ogAt"
        TekTelicWriteEncoder.TICK_RHUMIDITY         | 378    | "owF6"
        TekTelicWriteEncoder.TICK_LIGHT             | 45678  | "pbJu"
        TekTelicWriteEncoder.TICK_ACCELEROMETER     | 3618   | "pg4i"
        TekTelicWriteEncoder.TICK_MCU_TEMP          | 2078   | "pwge"
        TekTelicWriteEncoder.TICK_MOISTURE          | 657    | "qAKR"
        TekTelicWriteEncoder.TICK_ANALOG_INPUT      | 38309  | "qZWl"


    }


}


