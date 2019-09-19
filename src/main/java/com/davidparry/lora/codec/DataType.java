package com.davidparry.lora.codec;

import java.util.Objects;

public final class DataType implements CayenneType {

    private byte lpp;

    private String llpX;

    private byte channel;

    private String channelX;

    private int size;

    private boolean signed;

    private SensorKey key;

    private String label;

    private int deciPos;

    private DataType(Builder builder) {
        lpp = builder.lpp;
        llpX = builder.llpX;
        channel = builder.channel;
        channelX = builder.channelX;
        size = builder.size;
        signed = builder.signed;
        key = builder.key;
        label = builder.label;
        deciPos = builder.deciPos;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(DataType copy) {
        Builder builder = new Builder();
        builder.lpp = copy.getLpp();
        builder.llpX = copy.getLlpX();
        builder.channel = copy.getChannel();
        builder.channelX = copy.getChannelX();
        builder.size = copy.getSize();
        builder.signed = copy.isSigned();
        builder.key = copy.getKey();
        builder.label = copy.getLabel();
        builder.deciPos = copy.getDeciPos();
        return builder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DataType)) return false;
        DataType dataType = (DataType) o;
        return lpp == dataType.lpp &&
                channel == dataType.channel &&
                size == dataType.size &&
                signed == dataType.signed &&
                deciPos == dataType.deciPos &&
                Objects.equals(llpX, dataType.llpX) &&
                Objects.equals(channelX, dataType.channelX) &&
                key == dataType.key;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lpp, llpX, channel, channelX, size, signed, key, deciPos);
    }

    public byte getLpp() {
        return lpp;
    }

    public String getLlpX() {
        return llpX;
    }

    public byte getChannel() {
        return channel;
    }

    public String getChannelX() {
        return channelX;
    }

    public int getSize() {
        return size;
    }

    public boolean isSigned() {
        return signed;
    }

    public SensorKey getKey() {
        return key;
    }

    public String getLabel() {
        return label;
    }

    public int getDeciPos() {
        return deciPos;
    }

    @SuppressWarnings("PMD.AvoidFieldNameMatchingMethodName")
    public static final class Builder {
        private byte lpp;
        private String llpX;
        private byte channel;
        private String channelX;
        private int size;
        private boolean signed;
        private SensorKey key;
        private String label;
        private int deciPos;

        private Builder() {
        }

        public Builder withLpp(byte val) {
            lpp = val;
            return this;
        }

        public Builder withLlpX(String val) {
            llpX = val;
            return this;
        }

        public Builder withChannel(byte val) {
            channel = val;
            return this;
        }

        public Builder withChannelX(String val) {
            channelX = val;
            return this;
        }

        public Builder withSize(int val) {
            size = val;
            return this;
        }

        public Builder withSigned(boolean val) {
            signed = val;
            return this;
        }

        public Builder withKey(SensorKey val) {
            key = val;
            return this;
        }

        public Builder withLabel(String val) {
            label = val;
            return this;
        }

        public Builder withDeciPos(int val) {
            deciPos = val;
            return this;
        }

        public CayenneType build() {
            return new DataType(this);
        }
    }
}
