package com.davidparry.lora.codec;

import com.davidparry.lora.codec.internal.decoder.Decoder;

public interface Register {

    Decoder decoder(byte channel);

    CayenneType dataType(byte channel);
}
