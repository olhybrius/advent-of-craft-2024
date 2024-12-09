package eid;

import org.apache.commons.lang3.StringUtils;

public class EID {

    private enum ElfSex {
        SLOUBI('1'),
        GAGNA('2'),
        CATACT('3');

        private final char identifier;

        ElfSex(char identifier) {
            this.identifier = identifier;
        }

        public static ElfSex getByIdentifier(char identifier) {
            for (var elfSex : ElfSex.values()) {
                if (elfSex.identifier == identifier) {
                    return elfSex;
                }
            }
            throw new IllegalArgumentException("Unknown sex for identifier " + identifier + ".");
        }
    }

    private record ControlKey(int value) {
        private static final int CONTROL_KEY_MAX_VALUE = 97;

        public static ControlKey forNumber(int number) {
            return new ControlKey(CONTROL_KEY_MAX_VALUE - number % CONTROL_KEY_MAX_VALUE);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj instanceof ControlKey controlKey) {
                return this.value == controlKey.value;
            }
            if (obj instanceof Integer intValue) {
                return this.value == intValue;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Integer.hashCode(value);
        }
    }

    private static final int EID_LENGTH = 8;
    private static final int CONTROL_KEY_START_INDEX = 6;

    private EID(String value) {
        validateFormat(value);
        validateLength(value);
        validateSexIdentifier(value);
        validateControlKey(value);
    }

    public static EID parse(String value) {
        return new EID(value);
    }

    private static void validateSexIdentifier(String value) {
        ElfSex.getByIdentifier(value.charAt(0));
    }

    private void validateControlKey(String value) {
        var number = Integer.parseInt(value.substring(0, CONTROL_KEY_START_INDEX));
        var controlKey = Integer.parseInt(value.substring(CONTROL_KEY_START_INDEX, EID_LENGTH));
        if (!ControlKey.forNumber(number).equals(controlKey)) {
            throw new IllegalArgumentException("Invalid control key.");
        }
    }

    private void validateLength(String value) {
        if (value.length() != EID_LENGTH) {
            throw new IllegalArgumentException("EID must be " + EID_LENGTH + " characters long.");
        }
    }

    private void validateFormat(String value) {
        if (StringUtils.isBlank(value)) {
            throw new IllegalArgumentException("EID cannot be blank.");
        }
        try {
            Integer.parseInt(value);
        } catch (NumberFormatException numberFormatException) {
            throw new IllegalArgumentException("EID must only contain digits.");
        }
    }
}
