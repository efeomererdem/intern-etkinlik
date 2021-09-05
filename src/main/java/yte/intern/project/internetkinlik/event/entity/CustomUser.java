package yte.intern.project.internetkinlik.event.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import yte.intern.project.internetkinlik.common.entity.BaseEntity;
import yte.intern.project.internetkinlik.event.security.CustomUserRole;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@NoArgsConstructor
public class CustomUser extends BaseEntity implements UserDetails {

    private Long tcKimlikNumber;
    @Email(message = "Hatali Email")
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private CustomUserRole customUserRole;
    private Boolean locked=false;
    private Boolean enabled=true;

    public CustomUser(Long tcKimlikNumber, String email, String password, CustomUserRole customUserRole) {
        this.tcKimlikNumber = tcKimlikNumber;
        this.email = email;
        this.password = password;
        this.customUserRole = customUserRole;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority(customUserRole.name());
        return Collections.singletonList(authority);
    }

    public Long gettcKimlikNumber() {return tcKimlikNumber; }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}