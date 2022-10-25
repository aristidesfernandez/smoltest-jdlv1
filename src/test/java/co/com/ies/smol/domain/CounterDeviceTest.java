package co.com.ies.smol.domain;

import static org.assertj.core.api.Assertions.assertThat;

import co.com.ies.smol.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CounterDeviceTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CounterDevice.class);
        CounterDevice counterDevice1 = new CounterDevice();
        counterDevice1.setId(1L);
        CounterDevice counterDevice2 = new CounterDevice();
        counterDevice2.setId(counterDevice1.getId());
        assertThat(counterDevice1).isEqualTo(counterDevice2);
        counterDevice2.setId(2L);
        assertThat(counterDevice1).isNotEqualTo(counterDevice2);
        counterDevice1.setId(null);
        assertThat(counterDevice1).isNotEqualTo(counterDevice2);
    }
}
