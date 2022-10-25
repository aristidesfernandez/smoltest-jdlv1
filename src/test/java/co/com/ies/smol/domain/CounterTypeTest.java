package co.com.ies.smol.domain;

import static org.assertj.core.api.Assertions.assertThat;

import co.com.ies.smol.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CounterTypeTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CounterType.class);
        CounterType counterType1 = new CounterType();
        counterType1.setId(1L);
        CounterType counterType2 = new CounterType();
        counterType2.setId(counterType1.getId());
        assertThat(counterType1).isEqualTo(counterType2);
        counterType2.setId(2L);
        assertThat(counterType1).isNotEqualTo(counterType2);
        counterType1.setId(null);
        assertThat(counterType1).isNotEqualTo(counterType2);
    }
}
