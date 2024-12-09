package eid;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

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

    private static final int EID_LENGTH = 8;
    private static final int CONTROL_KEY_MAX_VALUE = 97;
    private static final int CONTROL_KEY_START_INDEX = 6;

    public EID(String value) {
        if (StringUtils.isBlank(value)) {
            throw new IllegalArgumentException("EID cannot be blank.");
        }
        if (value.length() != EID_LENGTH) {
            throw new IllegalArgumentException("EID must be " + EID_LENGTH + " characters long.");
        }
        ElfSex.getByIdentifier(value.charAt(0));
        var number = Integer.parseInt(value.substring(0, CONTROL_KEY_START_INDEX));
        var controlKey = Integer.parseInt(value.substring(CONTROL_KEY_START_INDEX, EID_LENGTH));
        if (CONTROL_KEY_MAX_VALUE - number % CONTROL_KEY_MAX_VALUE != controlKey) {
            throw new IllegalArgumentException("Invalid control key.");
        }
    }
}
