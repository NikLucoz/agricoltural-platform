package it.unicam.cs.agricultural_platform.repositories;

import it.unicam.cs.agricultural_platform.models.event.Event;
import it.unicam.cs.agricultural_platform.models.event.EventType;
import it.unicam.cs.agricultural_platform.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    public Event findEventById(long id);
    @Query("SELECT e FROM Event e WHERE e.name LIKE %:filter% OR e.description LIKE %:filter%")
    public List<Event> findEventsByFilter(String filter);
}
