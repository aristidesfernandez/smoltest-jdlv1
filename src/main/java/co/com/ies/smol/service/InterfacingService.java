package co.com.ies.smol.service;

import co.com.ies.smol.service.dto.InterfacingDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link co.com.ies.smol.domain.Interfacing}.
 */
public interface InterfacingService {
    /**
     * Save a interfacing.
     *
     * @param interfacingDTO the entity to save.
     * @return the persisted entity.
     */
    InterfacingDTO save(InterfacingDTO interfacingDTO);

    /**
     * Updates a interfacing.
     *
     * @param interfacingDTO the entity to update.
     * @return the persisted entity.
     */
    InterfacingDTO update(InterfacingDTO interfacingDTO);

    /**
     * Partially updates a interfacing.
     *
     * @param interfacingDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<InterfacingDTO> partialUpdate(InterfacingDTO interfacingDTO);

    /**
     * Get all the interfacings.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<InterfacingDTO> findAll(Pageable pageable);

    /**
     * Get the "id" interfacing.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<InterfacingDTO> findOne(Long id);

    /**
     * Delete the "id" interfacing.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
