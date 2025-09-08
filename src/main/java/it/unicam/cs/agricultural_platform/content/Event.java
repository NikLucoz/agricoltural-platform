package it.unicam.cs.agricultural_platform.content;

import it.unicam.cs.agricultural_platform.content.user.User;

import it.unicam.cs.agricultural_platform.repository.RepositoryItem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Event extends RepositoryItem {
    private String name;
    private String description;
    private LocalDateTime localDateTime;
    private String place;
    private List<User> guests;

    private EventType eventType;
    private List<User> participants;

    public Event(long id, String name, String description, LocalDateTime localDateTime, String place, EventType eventType) {
        super(id);
        this.name = name;
        this.description = description;
        this.localDateTime = localDateTime;
        this.place = place;
        this.guests = new ArrayList<>();
        this.eventType = eventType;
        this.participants = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setGuests(List<User> guests) {
        this.guests = guests;
    }

    public List<User> getGuests() {
        return guests;
    }

    public void addGuest(User user) {
        guests.add(user);
    }

    public void removeGuest(User user) {
        guests.remove(user);
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }

    public void removeParticipant(User user) {
        participants.remove(user);
    }

    public void addParticipant(User user) {
        participants.add(user);
    }
}
