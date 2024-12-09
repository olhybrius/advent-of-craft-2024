package eid;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.thenThrownBy;

class EIDTests {
    @Test
    void an_EID_should_not_be_null() {
        thenThrownBy(() -> new EID(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("EID cannot be null.");
    }
}