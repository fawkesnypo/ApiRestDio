package one.digitalinnovation.peoplemanagement.controller;

import java.util.List;

import one.digitalinnovation.peoplemanagement.dto.PersonDTO;
import one.digitalinnovation.peoplemanagement.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/")
    public String aniversariantes(){
        return personService.aniversariantes();
    }

    @GetMapping("/Person/All")
    public List<PersonDTO> GetAll(){
        return personService.GetAll();
    }

    @GetMapping("/Person/{id}")
    public ResponseEntity<PersonDTO> Get(@PathVariable(value = "id") int id){
       return personService.Get(id);
    }

    @PostMapping("/Person/Create")
    @ResponseStatus(HttpStatus.CREATED)
    public PersonDTO Post(@RequestBody @Valid PersonDTO pessoa)
    {
        return personService.Post(pessoa);
    }

    @PutMapping("/Person/Update/{id}")
    public ResponseEntity<PersonDTO> Put(@PathVariable(value = "id") int id, @RequestBody @Valid PersonDTO newPessoa)
    {
        return personService.Put(id, newPessoa);
    }

    @DeleteMapping("/Person/Delete/{id}")
    public String Delete(@PathVariable(value = "id") int id){
        return personService.delete(id);
    }
}
