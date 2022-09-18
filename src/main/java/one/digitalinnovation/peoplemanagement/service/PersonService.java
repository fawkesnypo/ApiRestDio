package one.digitalinnovation.peoplemanagement.service;

import one.digitalinnovation.peoplemanagement.entity.Person;
import one.digitalinnovation.peoplemanagement.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository _personRepository;

    public List<Person> GetAll(){
        return _personRepository.findAll();
    }

    public ResponseEntity<Person> Get(int id){
        _personRepository.findById(id);

        Optional<Person> pessoa = _personRepository.findById(id);

        if(pessoa.isPresent())
            return new ResponseEntity<Person>(pessoa.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public Person Post(Person pessoa)
    {
        return _personRepository.save(pessoa);
    }

    public ResponseEntity<Person> Put(int id, Person newPessoa)
    {
        Optional<Person> oldPessoa = _personRepository.findById(id);
        if(oldPessoa.isPresent()){
            Person pessoa = oldPessoa.get();
            pessoa.setNome(newPessoa.getNome());
            _personRepository.save(pessoa);
            return new ResponseEntity<Person>(pessoa, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public String delete(int id){
        try{
            _personRepository.deleteById(id);
        }catch (Exception ex){
            return "Ocorreu um erro ao tentar deletar o registro";
        }

        return "Registro deletado com sucesso!";
    }
}
