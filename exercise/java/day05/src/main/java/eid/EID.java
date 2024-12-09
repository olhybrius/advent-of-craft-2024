package eid;

public class EID {

    private static final int EID_LENGTH = 8;

    public EID(String value) {
        if (value == null) {
            throw new IllegalArgumentException("EID cannot be null.");
        }
        if (value.length() != EID_LENGTH) {
            throw new IllegalArgumentException("EID must be " + EID_LENGTH + " characters long.");
        }
    }
}
