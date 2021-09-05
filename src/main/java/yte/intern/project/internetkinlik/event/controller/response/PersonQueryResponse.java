package yte.intern.project.internetkinlik.event.controller.response;

import lombok.Getter;
import yte.intern.project.internetkinlik.event.entity.Person;

import javax.validation.constraints.Email;

@Getter
public class PersonQueryResponse {


    private final Long id;
    private final String  firstName;
    private final String  lastName;
    private final Long tcKimlikNumber;
    private final String email;


    public PersonQueryResponse(Person person) {
        this.id = person.getId();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.tcKimlikNumber = person.getTcKimlikNumber();
        this.email = person.getEmail();
    }
}
