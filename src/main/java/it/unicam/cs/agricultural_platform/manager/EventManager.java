package it.unicam.cs.agricultural_platform.manager;

import it.unicam.cs.agricultural_platform.content.Event;
import it.unicam.cs.agricultural_platform.content.EventType;
import it.unicam.cs.agricultural_platform.content.user.User;
import it.unicam.cs.agricultural_platform.repository.ItemRepository;
import it.unicam.cs.agricultural_platform.repository.Repository;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

public class EventManager {
    private final Repository<Event> repository;

    public EventManager() {
        this.repository = new ItemRepository<>();
    }

    public boolean create(String name, String description, LocalDateTime dateTime, String place, EventType type) {
        return repository.add(new Event(
                repository.getNextId(),
                name,
                description,
                dateTime,
                place,
                type
        )) != null;
    }

    public boolean inviteGuest(long eventId, User user) {
        try {
            Event e = repository.getItemByID(eventId);
            e.addGuest(user);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}
