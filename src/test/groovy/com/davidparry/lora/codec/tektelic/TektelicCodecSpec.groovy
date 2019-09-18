package com.davidparry.lora.codec.tektelic

import com.davidparry.lora.codec.Payload
import com.davidparry.lora.codec.internal.PayloadReader
import spock.lang.Specification

class TektelicCodecSpec extends Specification {


    def setup() {
    }

    def "for happy path type and decoder present "() {
        given:

        com.davidparry.lora.codec.Register register = Mock(com.davidparry.lora.codec.Register)
        PayloadReader reader = Mock(PayloadReader)
        TektelicHomeCodec tektelicCodec = new TektelicHomeCodec(register, reader)
        byte[] data = [TektelicHomeRegister.MOISTURE.getChannel(), TektelicHomeRegister.MOISTURE.getLpp(), 00]
        com.davidparry.lora.codec.internal.decoder.Decoder decoder = Mock(com.davidparry.lora.codec.internal.decoder.Decoder)
        com.davidparry.lora.codec.internal.sensor.Digital digital = new com.davidparry.lora.codec.internal.sensor.Digital(TektelicHomeRegister.MOISTURE, 0)

        when:
        Payload device = tektelicCodec.decode(data)

        then:
        1 * reader.load(data)
        1 * reader.isProcessing() >> true
        1 * reader.read() >> data[0]
        1 * register.dataType(data[0]) >> TektelicHomeRegister.MOISTURE
        1 * register.decoder(data[0]) >> decoder
        1 * decoder.decode(reader, TektelicHomeRegister.MOISTURE) >> digital
        device.getSensors().get(0).getValue() == 0
        1 * reader.isProcessing() >> false
    }

    def "for no type then no decoder "() {
        given:

        com.davidparry.lora.codec.Register register = Mock(com.davidparry.lora.codec.Register)
        PayloadReader reader = Mock(PayloadReader)
        TektelicHomeCodec tektelicCodec = new TektelicHomeCodec(register, reader)
        byte[] data = [TektelicHomeRegister.MOISTURE.getChannel(), TektelicHomeRegister.MOISTURE.getLpp(), 00]

        when:
        Payload device = tektelicCodec.decode(data)

        then:
        1 * reader.load(data)
        1 * reader.isProcessing() >> true
        2 * reader.read() >> data[0]
        1 * register.dataType(data[0]) >> null
        1 * register.decoder(data[0]) >> Mock(com.davidparry.lora.codec.internal.decoder.Decoder)
        1 * reader.isProcessing() >> true
        1 * register.dataType(data[0]) >> TektelicHomeRegister.BATTERY_VOLTAGE
        1 * register.decoder(data[0]) >> null
        1 * reader.isProcessing() >> false
        device.getSensors().size() == 0
    }


}


