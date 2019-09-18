package com.davidparry.lora.codec.internal.decoder;

import com.davidparry.lora.codec.CayenneType;
import com.davidparry.lora.codec.internal.PayloadReader;

public interface Decoder<T, R, D> {

    T decode(PayloadReader<R, D> payloadReader, CayenneType dataType);
}
