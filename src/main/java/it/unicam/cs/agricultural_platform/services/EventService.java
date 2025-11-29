package it.unicam.cs.agricultural_platform.services;

import it.unicam.cs.agricultural_platform.models.event.Event;
import it.unicam.cs.agricultural_platform.models.event.Partecipation;
import it.unicam.cs.agricultural_platform.models.user.User;
import it.unicam.cs.agricultural_platform.repositories.EventRepository;
import it.unicam.cs.agricultural_platform.repositories.PartecipantsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private PartecipantsRepository partecipantsRepository;

    public Event getEvent(long id){
        return eventRepository.findEventById(id);
    }

    public Event getEvent(String name){
        return eventRepository.findEventByName(name);
    }

    public List<Event> getEvents(){
        return eventRepository.findAll();
    }

    public boolean addEvent(Event event){
        try{
            eventRepository.save(event);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean updateEvent(Event event, Event updateEvent){
        if(updateEvent == null || event == null) return false;

        if(!event.getName().equals(updateEvent.getName())){
            event.setName(updateEvent.getName());
        }

        if(!event.getDescription().equals(updateEvent.getDescription())){
            event.setDescription(updateEvent.getDescription());
        }

        if(!event.getLocalDateTime().equals(updateEvent.getLocalDateTime())){
            event.setLocalDateTime(updateEvent.getLocalDateTime());
        }

        if(!event.getPlace().equals(updateEvent.getPlace())){
            event.setPlace(updateEvent.getPlace());
        }

        if(!event.getEventType().equals(updateEvent.getEventType())){
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

    public List<Partecipation> getUserPartecipations(long userId) {
        return partecipantsRepository.findByUser_Id(userId);
    }
}