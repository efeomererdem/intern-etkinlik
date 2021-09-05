package yte.intern.project.internetkinlik.event.security.login;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import yte.intern.project.internetkinlik.event.security.CustomUserService;

@Service
@AllArgsConstructor
public class LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    private final CustomUserService customUserService;

    public String auth(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        if(authentication.isAuthenticated())
        {
            return "LOGGED IN !";
        }
        else{
            return "ERROR";
        }
    }

}
