package com.davidparry.lora.codec;

public interface CayenneType {

    byte getLpp();

    String getLlpX();

    byte getChannel();

    String getChannelX();

    int getSize();

    boolean isSigned();

    SensorKey getKey();

    String getLabel();

    int getDeciPos();

}
