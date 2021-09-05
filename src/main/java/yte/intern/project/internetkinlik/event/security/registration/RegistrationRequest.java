package yte.intern.project.internetkinlik.event.security.registration;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {

    private final Long tcKimlikNumber;
    private final String password;
    private final String email;

}
