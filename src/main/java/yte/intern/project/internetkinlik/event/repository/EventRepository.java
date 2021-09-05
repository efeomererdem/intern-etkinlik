package yte.intern.project.internetkinlik.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import yte.intern.project.internetkinlik.event.entity.Event;

public interface EventRepository extends JpaRepository<Event, Long> {

}
