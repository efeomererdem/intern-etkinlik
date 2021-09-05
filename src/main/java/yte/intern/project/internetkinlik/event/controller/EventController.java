package yte.intern.project.internetkinlik.event.controller;

import org.springframework.web.bind.annotation.*;
import yte.intern.project.internetkinlik.common.dto.MessageResponse;
import yte.intern.project.internetkinlik.event.controller.request.AddEventRequest;
import yte.intern.project.internetkinlik.event.controller.request.AddUserToEventRequest;
import yte.intern.project.internetkinlik.event.controller.request.UpdateEventRequest;
import yte.intern.project.internetkinlik.event.controller.response.EventQueryResponse;
import yte.intern.project.internetkinlik.event.entity.Event;
import yte.intern.project.internetkinlik.event.service.EventService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class EventController {

    private final EventService eventService;
    public EventController(final EventService eventService) {this.eventService = eventService;}

    @GetMapping("/getEvents")
    public List<EventQueryResponse> getAllEvents(){
        return eventService.getAllEvents()
                .stream()
                .map(event -> new EventQueryResponse(event))
                .toList();
    }
    @GetMapping("/getEvents/{id}")
    public Event getEventById(@PathVariable Long id) {
        return eventService.getEventById(id); }

    @PostMapping("/events")
    public MessageResponse addEvent(@Valid @RequestBody AddEventRequest addEventRequest){
        Event event = addEventRequest.toEntity();
        return eventService.addEvent(event);
    }

    @PutMapping("/events/{id}")
    public MessageResponse updateEvent(@PathVariable Long id, @Valid @RequestBody UpdateEventRequest updateEventRequest){
        return eventService.updateEvent(id, updateEventRequest.toEntity());
    }


    @DeleteMapping("/events/{id}")
    public MessageResponse deleteEvent(@PathVariable Long id) { return eventService.deleteEvent(id); }

    @PostMapping("/eventRegistration/{id}/registration")
    public MessageResponse addUserToEvent(@PathVariable Long id, @RequestBody AddUserToEventRequest addUserToEventRequest){
        return eventService.addUserToEvent(id, addUserToEventRequest.toEntiy());
    }

}
