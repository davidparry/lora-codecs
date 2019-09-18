package com.davidparry.lora.codec;

public interface CayenneType {

    byte getLpp();

    String getLlpX();

    byte getChannel();

    String getChannelX();

    int getSize();

    boolean isSigned();

    SensorType getKey();

    String getLabel();

    int getDeciPos();

}
