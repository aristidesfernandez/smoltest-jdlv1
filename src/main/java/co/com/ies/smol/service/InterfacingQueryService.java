package co.com.ies.smol.service;

import co.com.ies.smol.domain.*; // for static metamodels
import co.com.ies.smol.domain.Interfacing;
import co.com.ies.smol.repository.InterfacingRepository;
import co.com.ies.smol.service.criteria.InterfacingCriteria;
import co.com.ies.smol.service.dto.InterfacingDTO;
import co.com.ies.smol.service.mapper.InterfacingMapper;
import java.util.List;
import javax.persistence.criteria.JoinType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link Interfacing} entities in the database.
 * The main input is a {@link InterfacingCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link InterfacingDTO} or a {@link Page} of {@link InterfacingDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class InterfacingQueryService extends QueryService<Interfacing> {

    private final Logger log = LoggerFactory.getLogger(InterfacingQueryService.class);

    private final InterfacingRepository interfacingRepository;

    private final InterfacingMapper interfacingMapper;

    public InterfacingQueryService(InterfacingRepository interfacingRepository, InterfacingMapper interfacingMapper) {
        this.interfacingRepository = interfacingRepository;
        this.interfacingMapper = interfacingMapper;
    }

    /**
     * Return a {@link List} of {@link InterfacingDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<InterfacingDTO> findByCriteria(InterfacingCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Interfacing> specification = createSpecification(criteria);
        return interfacingMapper.toDto(interfacingRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link InterfacingDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<InterfacingDTO> findByCriteria(InterfacingCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Interfacing> specification = createSpecification(criteria);
        return interfacingRepository.findAll(specification, page).map(interfacingMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(InterfacingCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Interfacing> specification = createSpecification(criteria);
        return interfacingRepository.count(specification);
    }

    /**
     * Function to convert {@link InterfacingCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Interfacing> createSpecification(InterfacingCriteria criteria) {
        Specification<Interfacing> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Interfacing_.id));
            }
            if (criteria.getIsAssigned() != null) {
                specification = specification.and(buildSpecification(criteria.getIsAssigned(), Interfacing_.isAssigned));
            }
            if (criteria.getIpAddress() != null) {
                specification = specification.and(buildStringSpecification(criteria.getIpAddress(), Interfacing_.ipAddress));
            }
            if (criteria.getHash() != null) {
                specification = specification.and(buildStringSpecification(criteria.getHash(), Interfacing_.hash));
            }
            if (criteria.getSerial() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSerial(), Interfacing_.serial));
            }
            if (criteria.getVersion() != null) {
                specification = specification.and(buildStringSpecification(criteria.getVersion(), Interfacing_.version));
            }
            if (criteria.getPort() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPort(), Interfacing_.port));
            }
            if (criteria.getEstablishmentId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getEstablishmentId(),
                            root -> root.join(Interfacing_.establishment, JoinType.LEFT).get(Establishment_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
