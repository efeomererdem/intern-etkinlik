package yte.intern.project.internetkinlik.event.service;

import org.springframework.stereotype.Service;
import yte.intern.project.internetkinlik.common.dto.MessageResponse;
import yte.intern.project.internetkinlik.common.dto.MessageType;
import yte.intern.project.internetkinlik.event.entity.Event;
import yte.intern.project.internetkinlik.event.entity.Person;
import yte.intern.project.internetkinlik.event.repository.EventRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(final EventRepository eventRepository){
        this.eventRepository = eventRepository;
    }

    @Transactional
    public MessageResponse addEvent(Event event) {
        int compareStartDate = event.getStartDate().compareTo(event.getEndDate());
        if(compareStartDate > 0){
            return new MessageResponse("The Event Cannot End Without Starting !", MessageType.ERROR);
        }

        eventRepository.save(event);
        return new MessageResponse("Event has been saved to database successfully !", MessageType.SUCCESS);
    }

    public List<Event> getAllEvents() {
        List<Event> allEvents = eventRepository.findAll();
        /*for(int i=0; i<allEvents.size(); i++)
        {
            int compareDate = allEvents.get(i).getStartDate().compareTo(LocalDate.now());
            if(compareDate < 0)
            {
                allEvents.remove(i);
            }
        }*/
        return allEvents;
    }

    public Event getEventById(Long id){
        Event event =  eventRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Event with this id %s is not found".formatted(id)));
        return event;
    }

    public MessageResponse updateEvent(Long id, Event newevent){
        int compareStartDate = newevent.getStartDate().compareTo(newevent.getEndDate());
        if(compareStartDate > 0){
            return new MessageResponse("The Event Cannot End Without Starting !", MessageType.ERROR);
        }
        int compareStartDate1 = LocalDate.now().compareTo(newevent.getStartDate());
        if(compareStartDate1 > 0){
            return new MessageResponse("You Cannot Update This Event Since it is Already Started !", MessageType.ERROR);
        }
        Event event = eventRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Event with this id %s is not found".formatted(id)));
        event.updateEvent(newevent);
        eventRepository.save(event);
        return new MessageResponse("Event with id %s has been updated successfully !".formatted(id), MessageType.SUCCESS);
    }

    public  MessageResponse deleteEvent(Long id){
        Event event = eventRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Event with this id %s is not found".formatted(id)));
        eventRepository.deleteById(id);
        return new MessageResponse("Event with id %s has been deleted successfully !".formatted(id), MessageType.SUCCESS);
    }

    @Transactional
    public MessageResponse addUserToEvent(Long id, Person toBeAddedPerson){
        Event event = eventRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Event with this id %s is not found".formatted(id)));
        MessageResponse messageResponse = event.canAddPerson(toBeAddedPerson);
        if(messageResponse.hasError()){
            return messageResponse;
        }
        event.addPerson(toBeAddedPerson);
        event.incrementRegistrations();
        return new MessageResponse("Participant %s has been added successfully !".formatted(toBeAddedPerson.getTcKimlikNumber()), MessageType.SUCCESS);
    }

}
