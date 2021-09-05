package yte.intern.project.internetkinlik.event.security;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import yte.intern.project.internetkinlik.common.dto.MessageResponse;
import yte.intern.project.internetkinlik.common.dto.MessageType;
import yte.intern.project.internetkinlik.event.entity.CustomUser;

@Service
@AllArgsConstructor
public class CustomUserService implements UserDetailsService {

    private final static String USER_NOT_FOUND =  "User with email %s not found";
    private final CustomUserRepository customUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return customUserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND, email)));
    }

    public String signUpUser(CustomUser customUser) {
        boolean userExists = customUserRepository
                .findByEmail(customUser.getEmail())
                .isPresent();
        if (userExists) {
            return "This Email has Already Registered !";
        }
        boolean tcExists = customUserRepository
                .findBytcKimlikNumber(customUser.gettcKimlikNumber())
                .isPresent();
        if (tcExists) {
            return "This TC Kimlik Number has Already Registered !";
        }
        String encodedPass = bCryptPasswordEncoder.encode(customUser.getPassword());
        customUser.setPassword(encodedPass);
        customUserRepository.save(customUser);
        return "Registered Successfully !";
    }


}