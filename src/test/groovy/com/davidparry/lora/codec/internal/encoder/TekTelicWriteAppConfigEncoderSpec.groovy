package com.davidparry.lora.codec.internal.encoder


import spock.lang.Specification

class TekTelicWriteAppConfigEncoderSpec extends Specification {
    def setup() {

    }

    def "for the given #bytes enable or encode #value to turn to base64 #answer"() {
        given:
        TekTelicWriteAppConfigEncoder encoder = new TekTelicWriteAppConfigEncoder()

        when:
        String result = encoder.encode(key, notused)

        then:
        result == answer

        where:
        key                           | notused | answer
        ConfigChoice.APP              | 1       | "8CAA"
        ConfigChoice.APP_LORA         | 0       | "8GAA"
        ConfigChoice.APP_LORA_RESTART | 234567  | "8GAB"
        ConfigChoice.APP_RESTART      | 9       | "8CAB"
        ConfigChoice.LORA_RESTART     | 86400   | "8EAB"
        ConfigChoice.LORA             | 1       | "8EAA"


    }


}


