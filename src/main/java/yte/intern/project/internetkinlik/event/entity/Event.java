package yte.intern.project.internetkinlik.event.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import yte.intern.project.internetkinlik.common.dto.MessageResponse;
import yte.intern.project.internetkinlik.common.dto.MessageType;
import yte.intern.project.internetkinlik.common.entity.BaseEntity;

import javax.persistence.*;
import java.awt.print.Book;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
public class Event extends BaseEntity {

    private String eventName;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer numberOfParticipants;
    private Integer numberOfRegistrations;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "event_id")
    private Set<Person> people = new HashSet<>();

    public Event(final String eventName, final LocalDate startDate, final LocalDate endDate, final Integer numberOfParticipants, final Integer numberOfRegistrations) {
        this.eventName = eventName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.numberOfParticipants = numberOfParticipants;
        this.numberOfRegistrations= numberOfRegistrations;
    }
    public Event(final String eventName, final LocalDate startDate, final LocalDate endDate, final Integer numberOfParticipants){
        this.eventName = eventName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.numberOfParticipants = numberOfParticipants;
    }

    public MessageResponse canAddPerson(Person toBeAddedPerson){
        if(hasSamePerson(toBeAddedPerson)){
            return new MessageResponse("We already have a participant with this TC Number !", MessageType.ERROR);
        }
        if(numberOfRegistrations.equals(numberOfParticipants))
        {
            return new MessageResponse("Sorry the quota for this event is full !", MessageType.ERROR);
        }
        return new MessageResponse("",MessageType.SUCCESS);
    }

    private boolean hasSamePerson(Person toBeAddedPerson){
        return people.stream()
                .anyMatch(person -> person.hasSameTcKimlikNumberAs(toBeAddedPerson));
    }

    public  void addPerson(Person toBeAddedPerson){
        if(canAddPerson(toBeAddedPerson).hasError()){
            throw new IllegalArgumentException();
        }
        people.add(toBeAddedPerson);
    }
    public void incrementRegistrations(){
        numberOfRegistrations++;
    }

    public void updateEvent(Event newKullanici) {
        this.eventName = newKullanici.eventName;
        this.startDate = newKullanici.startDate;
        this.endDate = newKullanici.endDate;
        this.numberOfParticipants = newKullanici.numberOfParticipants;
    }

}
