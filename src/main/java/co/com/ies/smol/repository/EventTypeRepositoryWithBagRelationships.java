package co.com.ies.smol.repository;

import co.com.ies.smol.domain.EventType;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface EventTypeRepositoryWithBagRelationships {
    Optional<EventType> fetchBagRelationships(Optional<EventType> eventType);

    List<EventType> fetchBagRelationships(List<EventType> eventTypes);

    Page<EventType> fetchBagRelationships(Page<EventType> eventTypes);
}
