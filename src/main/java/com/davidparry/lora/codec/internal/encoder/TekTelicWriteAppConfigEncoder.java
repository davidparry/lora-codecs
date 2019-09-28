package com.davidparry.lora.codec.internal.encoder;

public final class TekTelicWriteAppConfigEncoder implements WriteEncoder<ConfigChoice> {

    @Override
    public String encode(ConfigChoice choice, int value) {
        StringBuffer result = new StringBuffer();
        switch (choice) {
            case APP: {
                result.append("8CAA");
                break;
            }
            case LORA: {
                result.append("8EAA");
                break;
            }
            case APP_LORA: {
                result.append("8GAA");
                break;
            }
            case APP_RESTART: {
                result.append("8CAB");
                break;
            }
            case LORA_RESTART: {
                result.append("8EAB");
                break;
            }
            default: {
                result.append("8GAB");
                break;
            }
        }
        return result.toString();
    }

}
