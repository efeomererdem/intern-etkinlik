package yte.intern.project.internetkinlik.event.controller.request;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import yte.intern.project.internetkinlik.event.entity.Person;

@RequiredArgsConstructor
@Getter
@ToString
public class AddPersonRequest {
    private final String  firstName;
    private final String  lastName;
    private final Long tcKimlikNumber;
    private final @javax.validation.constraints.Email String email;

    public Person toEntity(){
        return new Person(firstName, lastName, tcKimlikNumber, email);
    }
}
