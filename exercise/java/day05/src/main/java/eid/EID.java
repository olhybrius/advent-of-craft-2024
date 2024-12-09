package eid;

public class EID {
    public EID(String value) {
        if (value == null) {
            throw new IllegalArgumentException("EID cannot be null.");
        }
        if (value.length() != 8) {
            throw new IllegalArgumentException("EID must be 8 characters long.");
        }
    }
}
