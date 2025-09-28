package it.unicam.cs.agricultural_platform.models.event;

import it.unicam.cs.agricultural_platform.models.user.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private LocalDateTime localDateTime;
    private String place;

    @Enumerated(EnumType.STRING)
    private EventType eventType;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Partecipation> participants;

    public Event(long id, String name, String description, LocalDateTime localDateTime, String place, EventType eventType) {
        this.name = name;
        this.description = description;
        this.localDateTime = localDateTime;
        this.place = place;
        this.eventType = eventType;
        this.participants = new ArrayList<>();
    }

    public Event() {

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

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public List<Partecipation> getParticipants() {
        return participants;
    }

    public void removeParticipant(User user) {
        participants.removeIf(p -> p.getUser().equals(user));
    }

    public void addParticipant(User user) {
        participants.add(new Partecipation(this, user));
    }

    public boolean hasParticipant(User user) {
        for (Partecipation p : participants) {
            if (p.getUser().equals(user)) {
                return true;
            }
        }
        return false;
    }

    public Long getId() {
        return id;
    }
}
