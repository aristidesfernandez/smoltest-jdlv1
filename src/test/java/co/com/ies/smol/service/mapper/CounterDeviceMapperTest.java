package co.com.ies.smol.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CounterDeviceMapperTest {

    private CounterDeviceMapper counterDeviceMapper;

    @BeforeEach
    public void setUp() {
        counterDeviceMapper = new CounterDeviceMapperImpl();
    }
}
