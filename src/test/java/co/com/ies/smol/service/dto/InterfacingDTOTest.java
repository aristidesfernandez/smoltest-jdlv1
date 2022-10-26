package co.com.ies.smol.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import co.com.ies.smol.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class InterfacingDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InterfacingDTO.class);
        InterfacingDTO interfacingDTO1 = new InterfacingDTO();
        interfacingDTO1.setId(1L);
        InterfacingDTO interfacingDTO2 = new InterfacingDTO();
        assertThat(interfacingDTO1).isNotEqualTo(interfacingDTO2);
        interfacingDTO2.setId(interfacingDTO1.getId());
        assertThat(interfacingDTO1).isEqualTo(interfacingDTO2);
        interfacingDTO2.setId(2L);
        assertThat(interfacingDTO1).isNotEqualTo(interfacingDTO2);
        interfacingDTO1.setId(null);
        assertThat(interfacingDTO1).isNotEqualTo(interfacingDTO2);
    }
}
