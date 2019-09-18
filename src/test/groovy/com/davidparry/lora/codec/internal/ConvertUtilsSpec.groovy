package com.davidparry.lora.codec.internal

import org.apache.commons.codec.binary.Hex
import spock.lang.Specification

class ConvertUtilsSpec extends Specification {


    def setup() {
    }

    def "for hexToByte to unsignedToInt happy path "() {
        when:
        byte[] SIGNED = [104]
        byte[] UNSIGNED = [(byte) 200] // above 127 causes "sign extension" so will be (-56) int
        int s = ConvertUtil.unsignedToInt(SIGNED[0])
        int u = ConvertUtil.unsignedToInt(UNSIGNED[0])
        then:
        (byte) s == SIGNED[0]
        (byte) u == UNSIGNED[0]
    }

    def "for toDigit with invalid hex"() {
        when:
        char c = 0x03B87C43
        ConvertUtil.toDigit(c)
        then:
        thrown(IllegalArgumentException)
    }

    def "for hex #hex result is #answer"() {
        when:
        byte[] bytes = Hex.decodeHex(hex)
        int out = ConvertUtil.convertUnsignedByteArray(bytes)

        then:
        out == answer

        where:
        answer   | hex
        3600     | "0E10"
        86400    | "015180"
        91186400 | "056F64E0"
        1        | "01"
        0        | "00"
        254      | "FE"
        255      | "FF"
        256      | "0100"
        257      | "0101"
        65536    | "010000"
        65535    | "00FFFF"
        1        | "00000001"
    }

    def "for to many bytes to convert to int"() {
        when:
        byte[] bytes = Hex.decodeHex("0000000000")
        ConvertUtil.convertUnsignedByteArray(bytes)

        then:
        thrown(IllegalArgumentException)
    }


}


