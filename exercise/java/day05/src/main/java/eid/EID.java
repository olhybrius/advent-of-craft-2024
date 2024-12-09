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

    public EID(String value) {
        if (StringUtils.isBlank(value)) {
            throw new IllegalArgumentException("EID cannot be blank.");
        }
        if (value.length() != EID_LENGTH) {
            throw new IllegalArgumentException("EID must be " + EID_LENGTH + " characters long.");
        }
        ElfSex.getByIdentifier(value.charAt(0));
        if (97 - Integer.parseInt(value.substring(0, 6)) % 97 != Integer.parseInt(value.substring(6, EID_LENGTH))) {
            throw new IllegalArgumentException("Invalid control key.");
        }
    }
}
