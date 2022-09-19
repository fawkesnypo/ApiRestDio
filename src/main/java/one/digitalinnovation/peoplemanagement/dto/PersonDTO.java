package one.digitalinnovation.peoplemanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {

    private int id;

    @NotEmpty
    @Size(min = 3, max = 30,message = "O nome deve conter de 3 a 30 caracteres!")
    private String nome;

    @NotEmpty
    @CPF
    private String cpf;

    @NotEmpty
    private String dataNascimento;

    @NotEmpty
    @Valid
    private List<PhoneDTO> phones;
}
