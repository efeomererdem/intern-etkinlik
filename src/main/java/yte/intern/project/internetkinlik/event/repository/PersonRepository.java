package yte.intern.project.internetkinlik.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yte.intern.project.internetkinlik.event.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
