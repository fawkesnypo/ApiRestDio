package one.digitalinnovation.peoplemanagement.service;

import one.digitalinnovation.peoplemanagement.dto.PersonDTO;
import one.digitalinnovation.peoplemanagement.entity.Person;
import one.digitalinnovation.peoplemanagement.mapper.PersonMapper;
import one.digitalinnovation.peoplemanagement.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {

    @Autowired
    private PersonRepository _personRepository;
    private PersonMapper personMapper = PersonMapper.INSTANCE;

    public String aniversariantes(){

        Date date= new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        List<Person> personList = _personRepository.findAll()
                .stream()
                .filter(person -> {
                    List<String> list = List.of(person.getDataNascimento().split("-"));
                    int m = Integer.parseInt(list.get(1).toString());
                    return m == cal.get(Calendar.MONTH) + 1;
                })
                .toList();

        StringBuilder  retorno = new StringBuilder();
        retorno.append("Aniversariantes do mês:\n\n");
        personList.forEach( person -> retorno.append("\n"+person.getNome()+" "+person.getDataNascimento().toString()));

        return retorno.toString();
    }

    public List<PersonDTO> GetAll(){

        List<Person> person = _personRepository.findAll();

        return person.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ResponseEntity<PersonDTO> Get(int id){

        Optional<Person> pessoa = _personRepository.findById(id);

        if(pessoa.isPresent()){
            Person result = pessoa.get();
            return new ResponseEntity<PersonDTO>(personMapper.toDTO(result), HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public PersonDTO Post(PersonDTO pessoa)
    {
        return personMapper.toDTO(_personRepository.save(personMapper.toModel(pessoa)));
    }

    public ResponseEntity<PersonDTO> Put(int id, PersonDTO newPessoa)
    {
        Person newPerson = personMapper.toModel(newPessoa);
        Optional<Person> oldPessoa = _personRepository.findById(id);
        if(oldPessoa.isPresent()){

            Person pessoa = oldPessoa.get();

            if (newPessoa.getNome()!= null){
                pessoa.setNome(newPerson.getNome());
            }
            if (newPessoa.getCpf()!= null){
                pessoa.setCpf(newPerson.getCpf());
            }
            if (newPessoa.getDataNascimento()!= null){
                pessoa.setDataNascimento(newPerson.getDataNascimento());
            }
            if (newPessoa.getPhones()!= null){
                pessoa.setPhones(newPerson.getPhones());
            }

            _personRepository.save(pessoa);
            return new ResponseEntity<PersonDTO>(personMapper.toDTO(pessoa), HttpStatus.OK);
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
