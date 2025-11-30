package it.unicam.cs.agricultural_platform.facades;

import it.unicam.cs.agricultural_platform.dto.event.EventDTO;
import it.unicam.cs.agricultural_platform.middlewares.Middleware;
import it.unicam.cs.agricultural_platform.middlewares.event.EventMiddleware;
import it.unicam.cs.agricultural_platform.middlewares.event.EventParticipantsMiddleware;
import it.unicam.cs.agricultural_platform.models.Content;
import it.unicam.cs.agricultural_platform.models.event.Event;
import it.unicam.cs.agricultural_platform.models.event.Partecipation;
import it.unicam.cs.agricultural_platform.models.user.User;
import it.unicam.cs.agricultural_platform.services.EventService;
import it.unicam.cs.agricultural_platform.services.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
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

    private Middleware<EventDTO> eventMiddleware;

    @PostConstruct
    private void init(){
        this.eventMiddleware = Middleware.link(new EventMiddleware(eventService), new EventParticipantsMiddleware(eventService, userService));
    }

    public List<Event> getEvents() {
        return eventService.getEvents();
    }

    public Event getEvent(long id) {
        return eventService.getEvent(id);
    }

    public List<Event> getEvents(String filter) {
        return eventService.getEvents(filter);
    }

    public boolean addEvent(EventDTO eventDTO) {
        if(!eventMiddleware.handle(eventDTO)) return false;

        var event = eventDTO.fromDTO(eventDTO);
        return eventService.addEvent(event);
    }

    public boolean deleteEvent(long id) {
        return eventService.deleteEvent(id);
    }

    public boolean updateEvent(long id, EventDTO eventDTO) {
        if(!eventMiddleware.handle(eventDTO)) return false;

        var original = eventService.getEvent(id);
        var updatedEvent = EventDTO.fromDTO(eventDTO);

        return eventService.updateEvent(original, updatedEvent);
    }

    public List<Partecipation> getPartecipationsForUser(long userId) {
        if(userService.existsUser(userId)) {
            return eventService.getUserPartecipations(userId);
        }
        return new ArrayList<>();
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

    @Transactional
    public void removeOrphanPartecipationFor(User user) {
        var parteicpations = eventService.getUserPartecipations(user.getId());

        for(Partecipation partecipation : parteicpations) {
            partecipation.getEvent().removeParticipant(partecipation.getUser());
        }
    }
}