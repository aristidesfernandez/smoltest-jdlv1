package co.com.ies.smol.domain;

import static org.assertj.core.api.Assertions.assertThat;

import co.com.ies.smol.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class IslandTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Island.class);
        Island island1 = new Island();
        island1.setId(1L);
        Island island2 = new Island();
        island2.setId(island1.getId());
        assertThat(island1).isEqualTo(island2);
        island2.setId(2L);
        assertThat(island1).isNotEqualTo(island2);
        island1.setId(null);
        assertThat(island1).isNotEqualTo(island2);
    }
}
