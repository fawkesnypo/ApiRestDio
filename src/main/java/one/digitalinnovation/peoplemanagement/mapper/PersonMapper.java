package one.digitalinnovation.peoplemanagement.mapper;

import one.digitalinnovation.peoplemanagement.dto.PersonDTO;
import one.digitalinnovation.peoplemanagement.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    @Mapping(target = "dataNascimento", source = "dataNascimento",dateFormat = "dd-MM-YYYY")
    Person toModel(PersonDTO personDTO);

    PersonDTO toDTO(Person person);
}
