package co.com.ies.smol.repository;

import co.com.ies.smol.domain.CounterDevice;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the CounterDevice entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CounterDeviceRepository extends JpaRepository<CounterDevice, Long>, JpaSpecificationExecutor<CounterDevice> {}
