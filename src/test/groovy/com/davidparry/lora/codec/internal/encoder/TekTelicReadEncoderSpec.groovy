package com.davidparry.lora.codec.internal.encoder


import spock.lang.Specification

class TekTelicReadEncoderSpec extends Specification {
    def setup() {

    }

    def "for valid value #bytes to encode #expect Read only"() {
        given:
        TekTelicReadEncoder tekTelicEncoder = new TekTelicReadEncoder()

        when:
        String result = tekTelicEncoder.encode(bytes)

        then:
        expect == result

        where:
        bytes                                      | expect
        TekTelicReadEncoder.MOISTURE_SAMPLE_PERIOD | "Wg=="
        TekTelicReadEncoder.MOISTURE_ENABLED       | "XA=="
        TekTelicReadEncoder.MOISTURE_THRESHOLD     | "Ww=="
        TekTelicReadEncoder.MOISTURE_SAMPLE_PERIOD | "Wg=="
        TekTelicReadEncoder.TICK_SECONDS           | "IA=="
        TekTelicReadEncoder.TICK_ACCELEROMETER     | "Jg=="
        TekTelicReadEncoder.TICK_ANALOG_INPUT      | "KQ=="
        TekTelicReadEncoder.TICK_BATTERY           | "IQ=="
        TekTelicReadEncoder.TICK_MOISTURE          | "KA=="
        TekTelicReadEncoder.TICK_REED_SWITCH       | "JA=="
        TekTelicReadEncoder.TICK_RHUMIDITY         | "Iw=="
        TekTelicReadEncoder.TICK_TEMPERATURE       | "Ig=="
    }

    def "for valid values to encode Read only"() {
        given:
        TekTelicReadEncoder tekTelicEncoder = new TekTelicReadEncoder()

        when:
        String result = tekTelicEncoder.encode(TekTelicReadEncoder.MOISTURE_SAMPLE_PERIOD
                ,TekTelicReadEncoder.MOISTURE_THRESHOLD,TekTelicReadEncoder.MOISTURE_ENABLED)

        then:
        "Wltc" == result
    }


}


