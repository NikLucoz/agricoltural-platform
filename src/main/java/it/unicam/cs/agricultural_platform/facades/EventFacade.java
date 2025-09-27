package it.unicam.cs.agricultural_platform.facades;

import it.unicam.cs.agricultural_platform.models.event.Event;
import it.unicam.cs.agricultural_platform.models.event.Partecipation;
import it.unicam.cs.agricultural_platform.models.user.User;
import it.unicam.cs.agricultural_platform.services.EventService;
import it.unicam.cs.agricultural_platform.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EventFacade {

    @Autowired
    private EventService eventService;
    @Autowired
    private UserService userService;

    public List<Event> getEvents() {
        return eventService.getEvents();
    }

    public Event getEvent(long id) {
        return eventService.getEvent(id);
    }

    public Event getEvent(String name) {
        return eventService.getEvent(name);
    }

    public boolean addEvent(Event event) {
        return eventService.addEvent(event);
    }

    public boolean deleteEvent(long id) {
        return eventService.deleteEvent(id);
    }

    public boolean updateEvent(long id, Event event) {
        return eventService.updateEvent(id, event);
    }

    // ==== Events Participants ====

    public List<Partecipation> getParticipants(long id) {
        return eventService.getParticipants(id);
    }

    public boolean deleteParticipant(long id, long userId) {
        if(!userService.existsUser(userId)) return false;
        var user = userService.getUserById(userId);
        return eventService.deleteParticipants(id, user);
    }

    public boolean addParticipant(long id, long userId) {
        if(!userService.existsUser(userId)) return false;
        var user = userService.getUserById(userId);
        return eventService.addParticipant(id, user);
    }

    public boolean addParticipants(long id, List<Long> usersIds) {
        var users = new ArrayList<User>();
        for (long userId: usersIds) {
            if(!userService.existsUser(userId))return false;
            users.add(userService.getUserById(userId));
        }

        return eventService.addParticipants(id, users);
    }
}