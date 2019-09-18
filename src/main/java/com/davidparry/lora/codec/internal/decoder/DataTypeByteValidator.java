package com.davidparry.lora.codec.internal.decoder;

import com.davidparry.lora.codec.CayenneType;
import com.davidparry.lora.codec.internal.PayloadReader;

import java.util.logging.Logger;

public class DataTypeByteValidator implements DataTypeValidator {
    private static final Logger LOG = Logger.getLogger(DataTypeByteValidator.class.getName());

    @Override
    public boolean validate(PayloadReader data, CayenneType type) {
        boolean flag = false;
        if (type == null || data == null) {
            throw new IllegalArgumentException("Both payloadReader and DataType must not be null. type "
                    + type + "  reader " + data);
        }
        if (type.getLpp() == (Byte) data.read()) {
            flag = true;
        }
        return flag;
    }
}
