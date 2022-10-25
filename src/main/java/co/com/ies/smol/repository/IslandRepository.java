package co.com.ies.smol.repository;

import co.com.ies.smol.domain.Island;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Island entity.
 */
@SuppressWarnings("unused")
@Repository
public interface IslandRepository extends JpaRepository<Island, Long>, JpaSpecificationExecutor<Island> {}
