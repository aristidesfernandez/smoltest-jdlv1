package co.com.ies.smol.domain;

import static org.assertj.core.api.Assertions.assertThat;

import co.com.ies.smol.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class InterfacingTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Interfacing.class);
        Interfacing interfacing1 = new Interfacing();
        interfacing1.setId(1L);
        Interfacing interfacing2 = new Interfacing();
        interfacing2.setId(interfacing1.getId());
        assertThat(interfacing1).isEqualTo(interfacing2);
        interfacing2.setId(2L);
        assertThat(interfacing1).isNotEqualTo(interfacing2);
        interfacing1.setId(null);
        assertThat(interfacing1).isNotEqualTo(interfacing2);
    }
}
