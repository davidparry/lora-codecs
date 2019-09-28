package com.davidparry.lora.codec.internal.encoder


import spock.lang.Specification

class TekTelicWriteEncoderSpec extends Specification {
    def setup() {

    }

    def "for "() {
        given:
        TekTelicWriteEncoder encoder = new TekTelicWriteEncoder()

        when:
        String result = encoder.encode(TekTelicWriteEncoder.MOISTURE_ENABLED, 1)

        then:
        result == answer

        where:
        bytes                                 | value | answer
        TekTelicWriteEncoder.MOISTURE_ENABLED | 1     | "3AE="
    }


}


