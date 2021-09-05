package yte.intern.project.internetkinlik.event.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;
import yte.intern.project.internetkinlik.common.dto.MessageResponse;
import yte.intern.project.internetkinlik.common.dto.MessageType;
import yte.intern.project.internetkinlik.event.entity.Event;
import yte.intern.project.internetkinlik.event.entity.Person;
import yte.intern.project.internetkinlik.event.repository.PersonRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class PersonService {

   private final PersonRepository personRepository;

   public PersonService(final PersonRepository personRepository) {
       this.personRepository = personRepository;
   }

   @Transactional
   public MessageResponse addPerson(Person person){
       personRepository.save(person);
       return new MessageResponse("User has been saved to database successfully !", MessageType.SUCCESS);
   }

   public List<Person> getAllPerson(){
       return personRepository.findAll();
   }

    public  MessageResponse deletePerson(Long id){
        Person person = personRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Event with this id %s is not found".formatted(id)));
        personRepository.deleteById(id);
        return new MessageResponse("Person with id %s has been deleted successfully !".formatted(id), MessageType.SUCCESS);
    }


}
