package co.com.ies.smol.repository;

import co.com.ies.smol.domain.DeviceEstablishment;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the DeviceEstablishment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DeviceEstablishmentRepository
    extends JpaRepository<DeviceEstablishment, Long>, JpaSpecificationExecutor<DeviceEstablishment> {}
