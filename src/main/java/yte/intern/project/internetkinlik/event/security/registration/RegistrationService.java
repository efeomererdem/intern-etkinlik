package yte.intern.project.internetkinlik.event.security.registration;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import yte.intern.project.internetkinlik.event.entity.CustomUser;
import yte.intern.project.internetkinlik.event.security.CustomUserRole;
import yte.intern.project.internetkinlik.event.security.CustomUserService;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final CustomUserService customUserService;


    public String register(RegistrationRequest request){
        return customUserService.signUpUser(
                new CustomUser(
                        request.getTcKimlikNumber(),
                        request.getEmail(),
                        request.getPassword(),
                        CustomUserRole.USER
                )
        );
    }
}
