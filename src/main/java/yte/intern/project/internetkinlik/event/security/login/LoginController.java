package yte.intern.project.internetkinlik.event.security.login;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import yte.intern.project.internetkinlik.event.security.registration.RegistrationRequest;

@RestController
@RequestMapping("/authenticate")
@AllArgsConstructor
@CrossOrigin("http://localhost:3000")
public class LoginController {

    private LoginService loginService;

    @PostMapping
    public String authenticate(@RequestBody LoginRequest loginRequest){
        return loginService.auth(loginRequest);
    }
}
