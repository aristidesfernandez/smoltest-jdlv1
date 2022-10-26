package co.com.ies.smol.web.rest;

import co.com.ies.smol.repository.IslandRepository;
import co.com.ies.smol.service.IslandQueryService;
import co.com.ies.smol.service.IslandService;
import co.com.ies.smol.service.criteria.IslandCriteria;
import co.com.ies.smol.service.dto.IslandDTO;
import co.com.ies.smol.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link co.com.ies.smol.domain.Island}.
 */
@RestController
@RequestMapping("/api")
public class IslandResource {

    private final Logger log = LoggerFactory.getLogger(IslandResource.class);

    private static final String ENTITY_NAME = "island";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final IslandService islandService;

    private final IslandRepository islandRepository;

    private final IslandQueryService islandQueryService;

    public IslandResource(IslandService islandService, IslandRepository islandRepository, IslandQueryService islandQueryService) {
        this.islandService = islandService;
        this.islandRepository = islandRepository;
        this.islandQueryService = islandQueryService;
    }

    /**
     * {@code POST  /islands} : Create a new island.
     *
     * @param islandDTO the islandDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new islandDTO, or with status {@code 400 (Bad Request)} if the island has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/islands")
    public ResponseEntity<IslandDTO> createIsland(@Valid @RequestBody IslandDTO islandDTO) throws URISyntaxException {
        log.debug("REST request to save Island : {}", islandDTO);
        if (islandDTO.getId() != null) {
            throw new BadRequestAlertException("A new island cannot already have an ID", ENTITY_NAME, "idexists");
        }
        IslandDTO result = islandService.save(islandDTO);
        return ResponseEntity
            .created(new URI("/api/islands/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /islands/:id} : Updates an existing island.
     *
     * @param id the id of the islandDTO to save.
     * @param islandDTO the islandDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated islandDTO,
     * or with status {@code 400 (Bad Request)} if the islandDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the islandDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/islands/{id}")
    public ResponseEntity<IslandDTO> updateIsland(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody IslandDTO islandDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Island : {}, {}", id, islandDTO);
        if (islandDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, islandDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!islandRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        IslandDTO result = islandService.update(islandDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, islandDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /islands/:id} : Partial updates given fields of an existing island, field will ignore if it is null
     *
     * @param id the id of the islandDTO to save.
     * @param islandDTO the islandDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated islandDTO,
     * or with status {@code 400 (Bad Request)} if the islandDTO is not valid,
     * or with status {@code 404 (Not Found)} if the islandDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the islandDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/islands/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<IslandDTO> partialUpdateIsland(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody IslandDTO islandDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Island partially : {}, {}", id, islandDTO);
        if (islandDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, islandDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!islandRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<IslandDTO> result = islandService.partialUpdate(islandDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, islandDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /islands} : get all the islands.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of islands in body.
     */
    @GetMapping("/islands")
    public ResponseEntity<List<IslandDTO>> getAllIslands(
        IslandCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get Islands by criteria: {}", criteria);
        Page<IslandDTO> page = islandQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /islands/count} : count all the islands.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/islands/count")
    public ResponseEntity<Long> countIslands(IslandCriteria criteria) {
        log.debug("REST request to count Islands by criteria: {}", criteria);
        return ResponseEntity.ok().body(islandQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /islands/:id} : get the "id" island.
     *
     * @param id the id of the islandDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the islandDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/islands/{id}")
    public ResponseEntity<IslandDTO> getIsland(@PathVariable Long id) {
        log.debug("REST request to get Island : {}", id);
        Optional<IslandDTO> islandDTO = islandService.findOne(id);
        return ResponseUtil.wrapOrNotFound(islandDTO);
    }

    /**
     * {@code DELETE  /islands/:id} : delete the "id" island.
     *
     * @param id the id of the islandDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/islands/{id}")
    public ResponseEntity<Void> deleteIsland(@PathVariable Long id) {
        log.debug("REST request to delete Island : {}", id);
        islandService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
