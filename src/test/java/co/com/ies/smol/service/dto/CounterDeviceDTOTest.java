package co.com.ies.smol.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import co.com.ies.smol.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CounterDeviceDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CounterDeviceDTO.class);
        CounterDeviceDTO counterDeviceDTO1 = new CounterDeviceDTO();
        counterDeviceDTO1.setId(1L);
        CounterDeviceDTO counterDeviceDTO2 = new CounterDeviceDTO();
        assertThat(counterDeviceDTO1).isNotEqualTo(counterDeviceDTO2);
        counterDeviceDTO2.setId(counterDeviceDTO1.getId());
        assertThat(counterDeviceDTO1).isEqualTo(counterDeviceDTO2);
        counterDeviceDTO2.setId(2L);
        assertThat(counterDeviceDTO1).isNotEqualTo(counterDeviceDTO2);
        counterDeviceDTO1.setId(null);
        assertThat(counterDeviceDTO1).isNotEqualTo(counterDeviceDTO2);
    }
}
