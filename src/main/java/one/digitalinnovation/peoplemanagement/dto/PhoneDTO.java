package one.digitalinnovation.peoplemanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import one.digitalinnovation.peoplemanagement.enums.PhoneType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhoneDTO {

    private int id;

    @NotEmpty
    @Size(min = 9,max = 13, message = "O número de telefone deve conter de 9 a 13 caracteres!")
    private String numero;

    @Enumerated(EnumType.STRING)
    private PhoneType tipo;
}
