package yte.intern.project.internetkinlik.event.controller.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import yte.intern.project.internetkinlik.event.entity.Event;

import java.time.LocalDate;

@RequiredArgsConstructor
@Getter
@ToString
public class AddEventRequest {
    private final String eventName;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final Integer numberOfParticipants;
    private final Integer numberOfRegistrations;

    public Event toEntity(){
        return new Event(eventName, startDate, endDate, numberOfParticipants, numberOfRegistrations);
    }
}
