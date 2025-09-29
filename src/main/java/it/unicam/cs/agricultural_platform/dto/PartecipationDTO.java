package it.unicam.cs.agricultural_platform.dto;

import it.unicam.cs.agricultural_platform.models.event.Event;
import it.unicam.cs.agricultural_platform.models.event.Partecipation;
import it.unicam.cs.agricultural_platform.models.user.User;
import it.unicam.cs.agricultural_platform.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class PartecipationDTO {

    private long id;
    private long eventId;
    private long userId;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long id) {
        this.eventId = id;
    }

    public long getUserId(){
        return this.userId;
    }

    public void setUserId(long userId){
        this.userId = userId;
    }

    public static PartecipationDTO fromPartecipation(Partecipation partecipation) {
        PartecipationDTO partecipationDTO = new PartecipationDTO();
        partecipationDTO.setId(partecipation.getId());
        partecipationDTO.setEventId(partecipation.getEvent().getId());
        partecipationDTO.setUserId(partecipation.getUser().getId());

        return partecipationDTO;
    }
}
