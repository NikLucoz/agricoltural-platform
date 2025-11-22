package it.unicam.cs.agricultural_platform.middlewares.event;

import it.unicam.cs.agricultural_platform.dto.event.EventDTO;
import it.unicam.cs.agricultural_platform.dto.event.PartecipationDTO;
import it.unicam.cs.agricultural_platform.middlewares.Middleware;
import it.unicam.cs.agricultural_platform.services.EventService;
import it.unicam.cs.agricultural_platform.services.UserService;

public class EventParticipantsMiddleware extends Middleware<EventDTO> {

    private final EventService eventService;
    private final UserService userService;

    public EventParticipantsMiddleware(EventService eventService, UserService userService){
        this.eventService = eventService;
        this.userService = userService;
    }

    public boolean handle(EventDTO data){
        for (PartecipationDTO partecipation : data.getParticipants()) {
            if(!userService.existsUser(partecipation.getUserId()))return false;
        }

        return handleNext(data);
    }
}
