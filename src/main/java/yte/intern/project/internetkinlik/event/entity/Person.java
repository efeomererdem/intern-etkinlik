package yte.intern.project.internetkinlik.event.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import yte.intern.project.internetkinlik.common.entity.BaseEntity;


import javax.persistence.Entity;
import javax.validation.constraints.Email;


@Entity
@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class Person extends BaseEntity{
    private String firstName;
    private String lastName;
    private Long tcKimlikNumber;
    @Email(message = "Hatali Email")
    private String email;


    public Person(final String firstName, final String lastName, final Long tcKimlikNumber, final @Email String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.tcKimlikNumber = tcKimlikNumber;
        this.email = email;
    }


    public boolean hasSameTcKimlikNumberAs(Person toBeAddedPerson) {
        return tcKimlikNumber.equals(toBeAddedPerson.tcKimlikNumber);
    }
}


