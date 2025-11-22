package it.unicam.cs.agricultural_platform.middlewares.event;

import it.unicam.cs.agricultural_platform.dto.event.EventDTO;
import it.unicam.cs.agricultural_platform.middlewares.Middleware;
import it.unicam.cs.agricultural_platform.services.EventService;

import java.time.LocalDateTime;

public class EventMiddleware extends Middleware<EventDTO> {

    private final EventService eventService;

    public EventMiddleware(EventService eventService){
        this.eventService = eventService;
    }


    public boolean handle(EventDTO data){
        if(data.getName().isBlank() || data.getName() == null) return false;
        if(data.getPlace().isBlank() || data.getPlace() == null) return false;
        if(data.getLocalDateTime() == null) return false;
        if(data.getEventType() == null) return false;
        if(data.getLocalDateTime().isBefore(LocalDateTime.now())) return false;

        return handleNext(data);
    }
}
