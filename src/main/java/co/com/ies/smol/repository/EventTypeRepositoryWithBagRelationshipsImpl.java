package co.com.ies.smol.repository;

import co.com.ies.smol.domain.EventType;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.annotations.QueryHints;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

/**
 * Utility repository to load bag relationships based on https://vladmihalcea.com/hibernate-multiplebagfetchexception/
 */
public class EventTypeRepositoryWithBagRelationshipsImpl implements EventTypeRepositoryWithBagRelationships {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<EventType> fetchBagRelationships(Optional<EventType> eventType) {
        return eventType.map(this::fetchIdEventTypes);
    }

    @Override
    public Page<EventType> fetchBagRelationships(Page<EventType> eventTypes) {
        return new PageImpl<>(fetchBagRelationships(eventTypes.getContent()), eventTypes.getPageable(), eventTypes.getTotalElements());
    }

    @Override
    public List<EventType> fetchBagRelationships(List<EventType> eventTypes) {
        return Optional.of(eventTypes).map(this::fetchIdEventTypes).orElse(Collections.emptyList());
    }

    EventType fetchIdEventTypes(EventType result) {
        return entityManager
            .createQuery(
                "select eventType from EventType eventType left join fetch eventType.idEventTypes where eventType is :eventType",
                EventType.class
            )
            .setParameter("eventType", result)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getSingleResult();
    }

    List<EventType> fetchIdEventTypes(List<EventType> eventTypes) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, eventTypes.size()).forEach(index -> order.put(eventTypes.get(index).getId(), index));
        List<EventType> result = entityManager
            .createQuery(
                "select distinct eventType from EventType eventType left join fetch eventType.idEventTypes where eventType in :eventTypes",
                EventType.class
            )
            .setParameter("eventTypes", eventTypes)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }
}
