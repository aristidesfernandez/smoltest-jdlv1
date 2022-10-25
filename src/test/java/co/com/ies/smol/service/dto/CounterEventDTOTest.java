package co.com.ies.smol.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import co.com.ies.smol.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CounterEventDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CounterEventDTO.class);
        CounterEventDTO counterEventDTO1 = new CounterEventDTO();
        counterEventDTO1.setId(1L);
        CounterEventDTO counterEventDTO2 = new CounterEventDTO();
        assertThat(counterEventDTO1).isNotEqualTo(counterEventDTO2);
        counterEventDTO2.setId(counterEventDTO1.getId());
        assertThat(counterEventDTO1).isEqualTo(counterEventDTO2);
        counterEventDTO2.setId(2L);
        assertThat(counterEventDTO1).isNotEqualTo(counterEventDTO2);
        counterEventDTO1.setId(null);
        assertThat(counterEventDTO1).isNotEqualTo(counterEventDTO2);
    }
}
