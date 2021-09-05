package yte.intern.project.internetkinlik.event.controller.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import yte.intern.project.internetkinlik.event.entity.Person;

@Getter
@RequiredArgsConstructor
public class AddUserToEventRequest {

    private final String  firstName;
    private final String  lastName;
    private final Long tcKimlikNumber;
    private final String email;

    public Person toEntiy(){
        return new Person(firstName, lastName, tcKimlikNumber, email);
    }
}
