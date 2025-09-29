package it.unicam.cs.agricultural_platform.repositories;

import it.unicam.cs.agricultural_platform.models.event.Event;
import it.unicam.cs.agricultural_platform.models.event.EventType;
import it.unicam.cs.agricultural_platform.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    public Event findEventById(long id);
    public Event findEventByName(String name);
    public List<Event> findAllEventsByParticipantsContains(User user);
    public List<Event> findAllEventsByEventType(EventType eventType);
}
