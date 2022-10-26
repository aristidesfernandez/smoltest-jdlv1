package co.com.ies.smol.repository;

import co.com.ies.smol.domain.Interfacing;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Interfacing entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InterfacingRepository extends JpaRepository<Interfacing, Long>, JpaSpecificationExecutor<Interfacing> {}
