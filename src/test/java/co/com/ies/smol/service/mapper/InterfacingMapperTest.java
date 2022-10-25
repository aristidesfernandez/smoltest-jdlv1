package co.com.ies.smol.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InterfacingMapperTest {

    private InterfacingMapper interfacingMapper;

    @BeforeEach
    public void setUp() {
        interfacingMapper = new InterfacingMapperImpl();
    }
}
