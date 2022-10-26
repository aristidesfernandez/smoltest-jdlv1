package co.com.ies.smol.service;

import co.com.ies.smol.domain.*; // for static metamodels
import co.com.ies.smol.domain.Island;
import co.com.ies.smol.repository.IslandRepository;
import co.com.ies.smol.service.criteria.IslandCriteria;
import co.com.ies.smol.service.dto.IslandDTO;
import co.com.ies.smol.service.mapper.IslandMapper;
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
 * Service for executing complex queries for {@link Island} entities in the database.
 * The main input is a {@link IslandCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link IslandDTO} or a {@link Page} of {@link IslandDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class IslandQueryService extends QueryService<Island> {

    private final Logger log = LoggerFactory.getLogger(IslandQueryService.class);

    private final IslandRepository islandRepository;

    private final IslandMapper islandMapper;

    public IslandQueryService(IslandRepository islandRepository, IslandMapper islandMapper) {
        this.islandRepository = islandRepository;
        this.islandMapper = islandMapper;
    }

    /**
     * Return a {@link List} of {@link IslandDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<IslandDTO> findByCriteria(IslandCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Island> specification = createSpecification(criteria);
        return islandMapper.toDto(islandRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link IslandDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<IslandDTO> findByCriteria(IslandCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Island> specification = createSpecification(criteria);
        return islandRepository.findAll(specification, page).map(islandMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(IslandCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Island> specification = createSpecification(criteria);
        return islandRepository.count(specification);
    }

    /**
     * Function to convert {@link IslandCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Island> createSpecification(IslandCriteria criteria) {
        Specification<Island> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Island_.id));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), Island_.description));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), Island_.name));
            }
        }
        return specification;
    }
}
