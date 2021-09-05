package yte.intern.project.internetkinlik.event.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import yte.intern.project.internetkinlik.event.entity.CustomUser;
import yte.intern.project.internetkinlik.event.entity.Person;

import java.util.Optional;

@Repository
public interface CustomUserRepository extends JpaRepository<CustomUser, Long> {
    Optional<CustomUser> findByEmail(String email);
    Optional<CustomUser> findBytcKimlikNumber(Long tcKimlikNumber);
}
