package com.davidparry.lora.codec.internal

import spock.lang.Specification

class ByteArrayPayloadReaderReaderSpec extends Specification {

    def setup() {
    }

    def "for #payload expected result #result"() {
        when:
        ByteArrayPayloadReaderReader byteArrayPayload = new ByteArrayPayloadReaderReader()

        then:
        byteArrayPayload.load(payload)
        byteArrayPayload.isProcessing() == result

        where:
        payload                 | result
        null                    | false
        (byte[]) [123, 200, 30] | true
        (byte[]) [123]          | true
        (byte[]) []             | false
    }

    def "for #pos value #read "() {
        when:
        ByteArrayPayloadReaderReader byteArrayPayload = new ByteArrayPayloadReaderReader()

        then:
        byteArrayPayload.load(payload)
        byteArrayPayload.isProcessing()
        byteArrayPayload.read() == r1
        byteArrayPayload.isProcessing()
        byteArrayPayload.read() == r2
        byteArrayPayload.isProcessing()
        byteArrayPayload.read() == r3
        byteArrayPayload.isProcessing()
        byteArrayPayload.read() == r4
        byteArrayPayload.isProcessing()
        byteArrayPayload.read() == r5
        byteArrayPayload.isProcessing()
        byteArrayPayload.read() == r6
        !byteArrayPayload.isProcessing()

        where:
        r1         | r2          | r3        | r4       | r5       | r6        | payload
        (byte) 123 | (byte) 127  | (byte) 10 | (byte) 0 | (byte) 1 | (byte) -2 | (byte[]) [123, 127, 10, 0, 1, -2]
        (byte) 123 | (byte) -128 | (byte) 10 | (byte) 0 | (byte) 1 | (byte) -2 | (byte[]) [123, -128, 10, 0, 1, -2]
    }

    def "for empty byte array throws Index out of bounds"() {
        given:
        ByteArrayPayloadReaderReader byteArrayPayload = new ByteArrayPayloadReaderReader()

        when:
        byteArrayPayload.load((byte[]) [])
        !byteArrayPayload.isProcessing()
        byteArrayPayload.read()

        then:
        thrown(ArrayIndexOutOfBoundsException)
    }

}


