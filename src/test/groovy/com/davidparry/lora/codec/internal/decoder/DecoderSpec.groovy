package com.davidparry.lora.codec.internal.decoder

import com.davidparry.lora.codec.CayenneType
import com.davidparry.lora.codec.internal.ByteArrayPayloadReaderReader
import com.davidparry.lora.codec.internal.PayloadReader
import com.davidparry.lora.codec.internal.sensor.*
import org.apache.commons.codec.binary.Base64
import org.apache.commons.codec.binary.Hex
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

import java.util.zip.CRC32

import static com.davidparry.lora.codec.tektelic.TektelicHomeRegister.*

class DecoderSpec extends Specification {
    //unkown pXVFeg==  <-- need to get back feedback on it
    @Shared
    byte[] reedCounterTrueSwitch = Base64.decodeBase64("AQD/CAQAAQ==")

    @Shared
    byte[] reedSwitchCounter = Base64.decodeBase64("AQAACAQAAQ==")

    //Sample 02  Threshhold 64 enabled 00
    @Shared
    byte[] mdReadResponse = Base64.decodeBase64("WgJbZFwA")

    @Shared
    byte[] tempRHVoltage = Base64.decodeBase64("A2cBBwRocQD/AS8=")

    @Shared
    DataTypeValidator validator = Mock(DataTypeValidator)

    def setup() {





    }



    def "for single pass of data to demonstrate a more code base connection before mocks of objects"() {
        given:
        DigitalDecoder decoder = new DigitalDecoder(new DataTypeByteValidator())
        CounterDecoder counterDecoder = new CounterDecoder(new DataTypeByteValidator())
        PayloadReader payloadReader = new ByteArrayPayloadReaderReader()

        when:
        // more in the data the ReedSwitch and Count
        payloadReader.load(reedCounterTrueSwitch)
        // increment acting like we read the channel to get the decoder and dataType
        payloadReader.read()
        Digital sensor = decoder.decode(payloadReader, MAGNETIC_SWITCH)
        payloadReader.read()
        Counter counter = counterDecoder.decode(payloadReader, MAGNETIC_SWITCH_COUNT)

        then:
        _ * validator.validate(_, _)
        sensor == reedSwitch
        counter == reedCount

        where:
        payload               | reedSwitch                      | reedCount
        reedCounterTrueSwitch | new Digital(MAGNETIC_SWITCH, 1) | new Counter(MAGNETIC_SWITCH_COUNT, 1)

    }

    def "for single pass of data with both Reed Switch FF and Reed Count 01"() {
        given:
        DigitalDecoder decoder = new DigitalDecoder(new DataTypeByteValidator())
        CounterDecoder counterDecoder = new CounterDecoder(new DataTypeByteValidator())
        PayloadReader payloadReader = new ByteArrayPayloadReaderReader()

        when:
        // more in the data the ReedSwitch and Count
        payloadReader.load(reedSwitchCounter)
        // increment acting like we read the channel to get the decoder and dataType
        payloadReader.read()
        Digital sensor = decoder.decode(payloadReader, MAGNETIC_SWITCH)
        payloadReader.read()
        Counter counter = counterDecoder.decode(payloadReader, MAGNETIC_SWITCH_COUNT)

        then:
        _ * validator.validate(_, _)
        sensor == reedSwitch
        counter == reedCount

        where:
        payload               | reedSwitch                      | reedCount
        reedCounterTrueSwitch | new Digital(MAGNETIC_SWITCH, 0) | new Counter(MAGNETIC_SWITCH_COUNT, 1)

    }

    def "for single pass of data temperature RH and voltage"() {
        given:
        TemperatureDecoder temperature = new TemperatureDecoder(new DataTypeByteValidator())
        HumidityDecoder humidityDecoder = new HumidityDecoder(new DataTypeByteValidator())
        AnalogVoltageDecoder voltageDecoder = new AnalogVoltageDecoder(new DataTypeByteValidator())
        PayloadReader payloadReader = new ByteArrayPayloadReaderReader()

        when:
        // more in the data temp than RH then Voltage
        payloadReader.load(tempRHVoltage)
        // increment acting like we read the channel to get the decoder and dataType
        payloadReader.read()
        Temperature temp = temperature.decode(payloadReader, TEMPERATURE)
        payloadReader.read()
        Humidity humidity = humidityDecoder.decode(payloadReader, HUMIDITY)
        payloadReader.read()
        Analog analog = voltageDecoder.decode(payloadReader, BATTERY_VOLTAGE)


        then:
        _ * validator.validate(_, _)
        temp == new Temperature(TEMPERATURE, 26.3)
        humidity == new Humidity(HUMIDITY, 56.5)
        analog == new Analog(BATTERY_VOLTAGE, 3.0300000000000002)

    }


    def "for single pass of data to read the moisture settings on the device"() {
        given:
        MoistureTransducerDecoder decoder = new MoistureTransducerDecoder()
        PayloadReader payloadReader = new ByteArrayPayloadReaderReader()

        when:
        // more in the data the sample period , Threshold, enable/disable
        payloadReader.load(mdReadResponse)
        // increment acting like we read the channel to get the decoder and dataType
        payloadReader.read()
        Digital sp = decoder.decode(payloadReader, SAMPLE_PERIOD)
        payloadReader.read()
        Digital threshold = decoder.decode(payloadReader, MD_THRESHOLD)
        payloadReader.read()
        Digital state = decoder.decode(payloadReader, MD_STATE)

        then:
        _ * validator.validate(_, _)
        sp == new Digital(SAMPLE_PERIOD, 2)
        threshold == new Digital(MD_THRESHOLD, 100)
        state == new Digital(MD_STATE, 0)

    }

    @Unroll
    def "for single byte decoder #tester using command #llp  #outcome byteValue #readValue"() {
        given:
        PayloadReader<Byte, byte[]> reader = Mock(PayloadReader)
        CayenneType type = Mock(CayenneType)

        when:
        Decoder decoder = tester
        def val = decoder.decode(reader, type)

        then:
        1 * reader.read() >> (byte) readValue
        val == outcome

        where:
        tester                         | llp                      | outcome                         | readValue
        new DigitalDecoder(validator)  | LIGHT_DETECTION.getLpp() | new Digital(LIGHT_DETECTION, 0) | (byte) 00
        new HumidityDecoder(validator) | HUMIDITY.getLpp()        | new Humidity(HUMIDITY, 86.5)    | (byte) -83
        new HumidityDecoder(validator) | HUMIDITY.getLpp()        | new Humidity(HUMIDITY, 80.0)    | (byte) -96
    }

    @Unroll
    def "for 2 byte decoder tester using command #llp #outcome byteOne #readOne byteTwo #readTwo"() {
        given:
        PayloadReader<Byte, byte[]> reader = Mock(PayloadReader)
        CayenneType type = Mock(CayenneType)

        when:
        Decoder decoder = tester
        def val = decoder.decode(reader, type)

        then:
        1 * reader.read() >> (byte) readOne
        1 * reader.read() >> (byte) readTwo
        _ * type.getSize() >> 2
        val == outcome

        where:
        tester                              | llp                      | outcome                            | readOne   | readTwo
        new AnalogVoltageDecoder(validator) | BATTERY_VOLTAGE.getLpp() | new Analog(BATTERY_VOLTAGE, 3.02)  | (byte) 01 | (byte) 46
        new TemperatureDecoder(validator)   | TEMPERATURE.getLpp()     | new Temperature(TEMPERATURE, 23.1) | (byte) 00 | (byte) -25
        new TemperatureDecoder(validator)   | TEMPERATURE.getLpp()     | new Temperature(TEMPERATURE, 22.8) | (byte) 00 | (byte) -28
    }

}


