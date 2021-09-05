package yte.intern.project.internetkinlik.event.controller.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import yte.intern.project.internetkinlik.event.entity.Event;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class UpdateEventRequest {
    private final String eventName;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final Integer numberOfParticipants;

    public Event toEntity(){
        return new Event(eventName, startDate, endDate, numberOfParticipants);
    }
}
