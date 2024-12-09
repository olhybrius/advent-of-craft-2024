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
        if (value.charAt(0) != '1' && value.charAt(0) != '2' && value.charAt(0) != '3') {
            throw new IllegalArgumentException("EID must start with a valid sex identifier.");
        }
    }
}
