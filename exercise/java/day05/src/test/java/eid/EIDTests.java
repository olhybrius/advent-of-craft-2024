package eid;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.thenNoException;
import static org.assertj.core.api.BDDAssertions.thenThrownBy;

class EIDTests {
    @Test
    void an_EID_should_not_be_null() {
        thenThrownBy(() -> new EID(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("EID cannot be blank.");
    }

    @Test
    void an_EID_should_not_be_empty() {
        thenThrownBy(() -> new EID(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("EID cannot be blank.");
    }

    @Test
    void an_EID_should_be_eight_characters_long() {
        thenThrownBy(() -> new EID("123456789"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("EID must be 8 characters long.");
        thenNoException().isThrownBy(() -> new EID("12345678"));
    }

    @Test
    void an_EID_should_start_with_a_valid_sex_identifier() {
        thenThrownBy(() -> new EID("02345678"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Unknown sex for identifier 0.");
        thenThrownBy(() -> new EID("42345678"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Unknown sex for identifier 4.");
        thenNoException().isThrownBy(() -> new EID("12345678"));
        thenNoException().isThrownBy(() -> new EID("22345678"));
        thenNoException().isThrownBy(() -> new EID("32345678"));
    }
}