package co.com.ies.smol.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import co.com.ies.smol.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class IslandDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(IslandDTO.class);
        IslandDTO islandDTO1 = new IslandDTO();
        islandDTO1.setId(1L);
        IslandDTO islandDTO2 = new IslandDTO();
        assertThat(islandDTO1).isNotEqualTo(islandDTO2);
        islandDTO2.setId(islandDTO1.getId());
        assertThat(islandDTO1).isEqualTo(islandDTO2);
        islandDTO2.setId(2L);
        assertThat(islandDTO1).isNotEqualTo(islandDTO2);
        islandDTO1.setId(null);
        assertThat(islandDTO1).isNotEqualTo(islandDTO2);
    }
}
