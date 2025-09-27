package it.unicam.cs.agricultural_platform.models.event;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.unicam.cs.agricultural_platform.models.user.User;
import jakarta.persistence.*;

@Entity
public class Partecipation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long partecipation_id;

    @ManyToOne
    @JoinColumn(name = "event_id")
    @JsonIgnore
    private Event event;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Partecipation(Event event, User user) {
        this.event = event;
        this.user = user;
    }

    public Partecipation() {}

    public Long getId() {
        return partecipation_id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
