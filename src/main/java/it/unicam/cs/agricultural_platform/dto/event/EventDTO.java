package it.unicam.cs.agricultural_platform.dto.event;

import it.unicam.cs.agricultural_platform.models.event.Event;
import it.unicam.cs.agricultural_platform.models.event.EventType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventDTO {

    private long id;
    private String name;
    private String description;
    private LocalDateTime localDateTime;
    private String place;
    private EventType eventType;
    private List<PartecipationDTO> participants;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
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

    public List<PartecipationDTO> getParticipants() {
        return participants;
    }

    public void setParticipants(List<PartecipationDTO> participants) {
        this.participants = participants;
    }

    public static EventDTO fromEvent(Event event){
        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(event.getId());
        eventDTO.setName(event.getName());
        eventDTO.setDescription(event.getDescription());
        eventDTO.setLocalDateTime(event.getLocalDateTime());
        eventDTO.setPlace(event.getPlace());
        eventDTO.setEventType(event.getEventType());

        List<PartecipationDTO> partecipationDTOList = new ArrayList<>();
        for(var partecipation : event.getParticipants()){
            partecipationDTOList.add(PartecipationDTO.fromPartecipation(partecipation));
        }
        eventDTO.setParticipants(partecipationDTOList);
        return eventDTO;
    }

    public static Event fromDTO(EventDTO eventDTO) {
        Event event = new Event();
        event.setName(eventDTO.getName());
        event.setDescription(eventDTO.getDescription());
        event.setLocalDateTime(eventDTO.getLocalDateTime());
        event.setPlace(eventDTO.getPlace());
        event.setEventType(eventDTO.getEventType());
        return event;
    }
}
