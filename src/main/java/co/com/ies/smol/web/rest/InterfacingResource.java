package co.com.ies.smol.web.rest;

import co.com.ies.smol.repository.InterfacingRepository;
import co.com.ies.smol.service.InterfacingQueryService;
import co.com.ies.smol.service.InterfacingService;
import co.com.ies.smol.service.criteria.InterfacingCriteria;
import co.com.ies.smol.service.dto.InterfacingDTO;
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
 * REST controller for managing {@link co.com.ies.smol.domain.Interfacing}.
 */
@RestController
@RequestMapping("/api")
public class InterfacingResource {

    private final Logger log = LoggerFactory.getLogger(InterfacingResource.class);

    private static final String ENTITY_NAME = "interfacing";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InterfacingService interfacingService;

    private final InterfacingRepository interfacingRepository;

    private final InterfacingQueryService interfacingQueryService;

    public InterfacingResource(
        InterfacingService interfacingService,
        InterfacingRepository interfacingRepository,
        InterfacingQueryService interfacingQueryService
    ) {
        this.interfacingService = interfacingService;
        this.interfacingRepository = interfacingRepository;
        this.interfacingQueryService = interfacingQueryService;
    }

    /**
     * {@code POST  /interfacings} : Create a new interfacing.
     *
     * @param interfacingDTO the interfacingDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new interfacingDTO, or with status {@code 400 (Bad Request)} if the interfacing has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/interfacings")
    public ResponseEntity<InterfacingDTO> createInterfacing(@Valid @RequestBody InterfacingDTO interfacingDTO) throws URISyntaxException {
        log.debug("REST request to save Interfacing : {}", interfacingDTO);
        if (interfacingDTO.getId() != null) {
            throw new BadRequestAlertException("A new interfacing cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InterfacingDTO result = interfacingService.save(interfacingDTO);
        return ResponseEntity
            .created(new URI("/api/interfacings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /interfacings/:id} : Updates an existing interfacing.
     *
     * @param id the id of the interfacingDTO to save.
     * @param interfacingDTO the interfacingDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated interfacingDTO,
     * or with status {@code 400 (Bad Request)} if the interfacingDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the interfacingDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/interfacings/{id}")
    public ResponseEntity<InterfacingDTO> updateInterfacing(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody InterfacingDTO interfacingDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Interfacing : {}, {}", id, interfacingDTO);
        if (interfacingDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, interfacingDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!interfacingRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        InterfacingDTO result = interfacingService.update(interfacingDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, interfacingDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /interfacings/:id} : Partial updates given fields of an existing interfacing, field will ignore if it is null
     *
     * @param id the id of the interfacingDTO to save.
     * @param interfacingDTO the interfacingDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated interfacingDTO,
     * or with status {@code 400 (Bad Request)} if the interfacingDTO is not valid,
     * or with status {@code 404 (Not Found)} if the interfacingDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the interfacingDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/interfacings/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<InterfacingDTO> partialUpdateInterfacing(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody InterfacingDTO interfacingDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Interfacing partially : {}, {}", id, interfacingDTO);
        if (interfacingDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, interfacingDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!interfacingRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<InterfacingDTO> result = interfacingService.partialUpdate(interfacingDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, interfacingDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /interfacings} : get all the interfacings.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of interfacings in body.
     */
    @GetMapping("/interfacings")
    public ResponseEntity<List<InterfacingDTO>> getAllInterfacings(
        InterfacingCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get Interfacings by criteria: {}", criteria);
        Page<InterfacingDTO> page = interfacingQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /interfacings/count} : count all the interfacings.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/interfacings/count")
    public ResponseEntity<Long> countInterfacings(InterfacingCriteria criteria) {
        log.debug("REST request to count Interfacings by criteria: {}", criteria);
        return ResponseEntity.ok().body(interfacingQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /interfacings/:id} : get the "id" interfacing.
     *
     * @param id the id of the interfacingDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the interfacingDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/interfacings/{id}")
    public ResponseEntity<InterfacingDTO> getInterfacing(@PathVariable Long id) {
        log.debug("REST request to get Interfacing : {}", id);
        Optional<InterfacingDTO> interfacingDTO = interfacingService.findOne(id);
        return ResponseUtil.wrapOrNotFound(interfacingDTO);
    }

    /**
     * {@code DELETE  /interfacings/:id} : delete the "id" interfacing.
     *
     * @param id the id of the interfacingDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/interfacings/{id}")
    public ResponseEntity<Void> deleteInterfacing(@PathVariable Long id) {
        log.debug("REST request to delete Interfacing : {}", id);
        interfacingService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
