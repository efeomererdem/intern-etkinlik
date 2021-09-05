package yte.intern.project.internetkinlik.event.controller.response;

import lombok.Getter;
import yte.intern.project.internetkinlik.event.entity.Event;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;

@Getter
public class EventQueryResponse {

    private final Long id;
    private final String eventName;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final Integer numberOfParticipants;
    private final Integer numberOfRegistrations;

    public EventQueryResponse(Event event) {
        this.id = event.getId();
        this.eventName = event.getEventName();
        this.startDate = event.getStartDate();
        this.endDate = event.getEndDate();
        this.numberOfParticipants = event.getNumberOfParticipants();
        this.numberOfRegistrations = event.getNumberOfRegistrations();
    }
}
