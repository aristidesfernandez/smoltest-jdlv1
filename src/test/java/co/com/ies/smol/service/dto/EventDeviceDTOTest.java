package co.com.ies.smol.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import co.com.ies.smol.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EventDeviceDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(EventDeviceDTO.class);
        EventDeviceDTO eventDeviceDTO1 = new EventDeviceDTO();
        eventDeviceDTO1.setId(1L);
        EventDeviceDTO eventDeviceDTO2 = new EventDeviceDTO();
        assertThat(eventDeviceDTO1).isNotEqualTo(eventDeviceDTO2);
        eventDeviceDTO2.setId(eventDeviceDTO1.getId());
        assertThat(eventDeviceDTO1).isEqualTo(eventDeviceDTO2);
        eventDeviceDTO2.setId(2L);
        assertThat(eventDeviceDTO1).isNotEqualTo(eventDeviceDTO2);
        eventDeviceDTO1.setId(null);
        assertThat(eventDeviceDTO1).isNotEqualTo(eventDeviceDTO2);
    }
}
