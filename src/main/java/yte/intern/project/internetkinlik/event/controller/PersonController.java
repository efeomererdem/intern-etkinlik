package yte.intern.project.internetkinlik.event.controller;


import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import yte.intern.project.internetkinlik.common.dto.MessageResponse;
import yte.intern.project.internetkinlik.common.dto.MessageType;
import yte.intern.project.internetkinlik.event.controller.request.AddEventRequest;
import yte.intern.project.internetkinlik.event.controller.request.AddPersonRequest;
import yte.intern.project.internetkinlik.event.controller.response.EventQueryResponse;
import yte.intern.project.internetkinlik.event.controller.response.PersonQueryResponse;
import yte.intern.project.internetkinlik.event.entity.Event;
import yte.intern.project.internetkinlik.event.entity.Person;
import yte.intern.project.internetkinlik.event.service.PersonService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class PersonController {

    private final PersonService personService;
    public PersonController(final PersonService personService) {this.personService = personService;}

    @GetMapping
    public List<PersonQueryResponse> getAllPerson(){
        return personService.getAllPerson()
                .stream()
                .map(person -> new PersonQueryResponse(person))
                .toList();
    }

    @PostMapping
    public MessageResponse addPerson(@Valid @RequestBody AddPersonRequest addPersonRequest, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return new MessageResponse("Email or TCKimlikNumber is Not Valid !", MessageType.ERROR);
        }
        Person person = addPersonRequest.toEntity();
        return personService.addPerson(person);
    }

    @DeleteMapping("{id}")
    public MessageResponse deletePerson(@PathVariable Long id) { return personService.deletePerson(id); }

}

