package co.com.ies.smol.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import co.com.ies.smol.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CounterTypeDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CounterTypeDTO.class);
        CounterTypeDTO counterTypeDTO1 = new CounterTypeDTO();
        counterTypeDTO1.setId(1L);
        CounterTypeDTO counterTypeDTO2 = new CounterTypeDTO();
        assertThat(counterTypeDTO1).isNotEqualTo(counterTypeDTO2);
        counterTypeDTO2.setId(counterTypeDTO1.getId());
        assertThat(counterTypeDTO1).isEqualTo(counterTypeDTO2);
        counterTypeDTO2.setId(2L);
        assertThat(counterTypeDTO1).isNotEqualTo(counterTypeDTO2);
        counterTypeDTO1.setId(null);
        assertThat(counterTypeDTO1).isNotEqualTo(counterTypeDTO2);
    }
}
