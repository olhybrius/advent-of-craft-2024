package eid;

public class EID {
    public EID(String value) {
        if (value == null) {
            throw new IllegalArgumentException("EID cannot be null.");
        }
    }
}
