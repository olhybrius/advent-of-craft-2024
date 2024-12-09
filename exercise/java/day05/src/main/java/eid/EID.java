package eid;

import org.apache.commons.lang3.StringUtils;

public class EID {

    private static final int EID_LENGTH = 8;

    public EID(String value) {
        if (StringUtils.isBlank(value)) {
            throw new IllegalArgumentException("EID cannot be blank.");
        }
        if (value.length() != EID_LENGTH) {
            throw new IllegalArgumentException("EID must be " + EID_LENGTH + " characters long.");
        }
    }
}
