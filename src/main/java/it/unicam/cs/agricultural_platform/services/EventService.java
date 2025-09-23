package it.unicam.cs.agricultural_platform.services;

import it.unicam.cs.agricultural_platform.models.event.Event;
import it.unicam.cs.agricultural_platform.models.event.Partecipation;
import it.unicam.cs.agricultural_platform.models.user.User;
import it.unicam.cs.agricultural_platform.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    public Event getEvent(long id){
        return eventRepository.findEventById(id);
    }

    public List<Event> getEvents(){
        return eventRepository.findAll();
    }

    public boolean addEvent(Event event){
        if(event.getName().isBlank()) return false;
        if(event.getPlace().isBlank()) return false;
        if(event.getLocalDateTime() == null) return false;
        if(event.getEventType() == null) return false;
        if(event.getLocalDateTime().isBefore(LocalDateTime.now())) return false;

        try{
            eventRepository.save(event);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean updateEvent(long id, Event updateEvent){
        var event = eventRepository.findEventById(id);
        if(updateEvent == null || event == null) return false;

        if(updateEvent.getName() != null && !updateEvent.getName().isBlank()) {
            event.setName(updateEvent.getName());
        }
        if(updateEvent.getDescription() != null && !updateEvent.getDescription().isBlank()) {
            event.setDescription(updateEvent.getDescription());
        }
        if(updateEvent.getLocalDateTime() != null) {
            event.setLocalDateTime(updateEvent.getLocalDateTime());
        }
        if(updateEvent.getPlace() != null && !updateEvent.getPlace().isBlank()) {
            event.setPlace(updateEvent.getPlace());
        }
        if(updateEvent.getEventType() != null) {
            event.setEventType(updateEvent.getEventType());
        }
        eventRepository.save(event);
        return true;
    }

    public boolean deleteEvent(long id){
        if(eventRepository.existsById(id)){
            eventRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean existsEvent(long id){
        return eventRepository.existsById(id);
    }


    public List<Partecipation> getParticipants(long id) {
        if(!existsEvent(id)) return new ArrayList<Partecipation>();
        var event = eventRepository.findEventById(id);
        return event.getParticipants();
    }

    public boolean addParticipant(long id, User user){
        if(!existsEvent(id)) return false;
        var event = eventRepository.findEventById(id);
        if(event.hasParticipant(user)) return false;
        event.addParticipant(user);
        eventRepository.save(event);
        return true;
    }

    public boolean addParticipants(long id, List<User> Participants){
        if(Participants == null || Participants.isEmpty()) return false;
        if(!existsEvent(id)) return false;
        var event = eventRepository.findEventById(id);
        for (User user: Participants) {
            if(event.hasParticipant(user)) continue;
            event.addParticipant(user);
        }
        eventRepository.save(event);
        return true;
    }

    public boolean deleteParticipants(long id, User user){
        if(user == null) return false;
        if(!existsEvent(id)) return false;
        var event = eventRepository.findEventById(id);
        if(event.hasParticipant(user)){
            event.removeParticipant(user);
            eventRepository.save(event);
            return true;
        }
        return false;
    }

    public boolean hasParticipant(long id, User user){
        if(user == null) return false;
        if(!existsEvent(id)) return false;
        var event = eventRepository.findEventById(id);
        return event.hasParticipant(user);
    }
}