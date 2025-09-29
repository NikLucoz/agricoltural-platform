package it.unicam.cs.agricultural_platform.controllers;

import it.unicam.cs.agricultural_platform.dto.ContentDTO;
import it.unicam.cs.agricultural_platform.dto.EventDTO;
import it.unicam.cs.agricultural_platform.dto.PartecipationDTO;
import it.unicam.cs.agricultural_platform.dto.ProductDTO;
import it.unicam.cs.agricultural_platform.facades.EventFacade;
import it.unicam.cs.agricultural_platform.models.event.Event;
import it.unicam.cs.agricultural_platform.models.event.Partecipation;
import it.unicam.cs.agricultural_platform.models.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventFacade eventFacade;

    @GetMapping("/")
    public ResponseEntity<List<Event>> getEvents() {
        List<Event> userList = eventFacade.getEvents();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDTO> getEvent(@PathVariable long id) {
        Event event = eventFacade.getEvent(id);
        if(event == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        EventDTO eventDTO = EventDTO.fromEvent(event);

        return new ResponseEntity<>(eventDTO, HttpStatus.OK);
    }

    @GetMapping(params = "name")
    public ResponseEntity<EventDTO> getEventByName(@RequestParam String name) {
        Event event = eventFacade.getEvent(name);
        if(event == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        EventDTO eventDTO = EventDTO.fromEvent(event);

        return new ResponseEntity<>(eventDTO, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addEvent(@RequestBody EventDTO eventDTO) {
        if(!eventFacade.addEvent(eventDTO)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Object> deleteEvent(@PathVariable long id) {
        if(!eventFacade.deleteEvent(id)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<Object> updateEvent(@PathVariable long id, @RequestBody EventDTO eventDTO) {
        if(!eventFacade.updateEvent(id, eventDTO)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // ==== Events Participants ====

    @GetMapping("/{id}/participants")
    public ResponseEntity<List<PartecipationDTO>> getParticipants(@PathVariable long id) {
        List<Partecipation> partecipationList = eventFacade.getParticipants(id);
        if(partecipationList == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        List<PartecipationDTO> partecipationDTOList = partecipationList.stream().map(PartecipationDTO::fromPartecipation).collect(Collectors.toList());

        return new ResponseEntity<>(partecipationDTOList, HttpStatus.OK);
    }

    @DeleteMapping("/{id}/participants/delete/{userId}")
    public ResponseEntity<Object> deleteParticipant(@PathVariable long id, @PathVariable long userId) {
        if(!eventFacade.deleteParticipant(id, userId)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{id}/participants/add")
    public ResponseEntity<Object> addParticipant(@PathVariable long id, @RequestBody long userId) {
        if(!eventFacade.addParticipant(id, userId)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{id}/participants/addList")
    public ResponseEntity<Object> addParticipants(@PathVariable long id, @RequestBody List<Long> usersIds) {
        if(!eventFacade.addParticipants(id, usersIds)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
