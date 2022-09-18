package one.digitalinnovation.peoplemanagement.controller;

import java.util.List;
import one.digitalinnovation.peoplemanagement.entity.Person;
import one.digitalinnovation.peoplemanagement.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/Person")
    public List<Person> GetAll(){
        return personService.GetAll();
    }

    @GetMapping("/Person/{id}")
    public ResponseEntity<Person> Get(@PathVariable(value = "id") int id){
       return personService.Get(id);
    }

    @PostMapping("/Person")
    @ResponseStatus(HttpStatus.CREATED)
    public Person Post(@RequestBody Person pessoa)
    {
        return personService.Post(pessoa);
    }

    @RequestMapping(value = "/pessoa/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Person> Put(@PathVariable(value = "id") int id, @RequestBody Person newPessoa)
    {
        return personService.Put(id, newPessoa);
    }

    @DeleteMapping("/Person/{id}")
    public String Delete(@PathVariable(value = "id") int id){
        return personService.delete(id);
    }
}
