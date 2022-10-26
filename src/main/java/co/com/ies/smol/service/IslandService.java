package co.com.ies.smol.service;

import co.com.ies.smol.service.dto.IslandDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link co.com.ies.smol.domain.Island}.
 */
public interface IslandService {
    /**
     * Save a island.
     *
     * @param islandDTO the entity to save.
     * @return the persisted entity.
     */
    IslandDTO save(IslandDTO islandDTO);

    /**
     * Updates a island.
     *
     * @param islandDTO the entity to update.
     * @return the persisted entity.
     */
    IslandDTO update(IslandDTO islandDTO);

    /**
     * Partially updates a island.
     *
     * @param islandDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<IslandDTO> partialUpdate(IslandDTO islandDTO);

    /**
     * Get all the islands.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<IslandDTO> findAll(Pageable pageable);

    /**
     * Get the "id" island.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<IslandDTO> findOne(Long id);

    /**
     * Delete the "id" island.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
