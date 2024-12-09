package eid;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.thenNoException;
import static org.assertj.core.api.BDDAssertions.thenThrownBy;

class EIDTests {
    @Test
    void an_EID_cannot_be_null() {
        thenThrownBy(() ->  EID.parse(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("EID cannot be blank.");
    }

    @Test
    void an_EID_cannot_be_empty() {
        thenThrownBy(() -> EID.parse(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("EID cannot be blank.");
    }

    @Test
    void an_EID_must_be_eight_characters_long() {
        thenThrownBy(() -> EID.parse("123456789"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("EID must be 8 characters long.");
    }

    @Test
    void an_EID_must_only_contain_digits() {
        thenThrownBy(() -> EID.parse("a23456789"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("EID must only contain digits.");
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 4, 5, 6, 7, 8, 9})
    void exception_is_thrown_for_invalid_sex_identifier(int identifier) {
        thenThrownBy(() -> EID.parse(identifier + "2345678"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Unknown sex for identifier " + identifier + ".");
    }

    @ParameterizedTest
    @ValueSource(strings = {"19800767", "29800774", "39800781"})
    void no_exception_is_thrown_for_valid_sex_identifier(String fullEid) {
        thenNoException().isThrownBy(() -> EID.parse(fullEid));
    }

    @Test
    void an_EID_cannot_be_instantiated_with_an_invalid_control_key() {
        thenThrownBy(() -> EID.parse(  "29800767"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid control key.");
    }
}