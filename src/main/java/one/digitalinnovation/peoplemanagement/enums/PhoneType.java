package one.digitalinnovation.peoplemanagement.enums;

import lombok.Getter;
import lombok.AllArgsConstructor;

@Getter
@AllArgsConstructor
public enum PhoneType {

    RESIDENCIAL("Residencial"),
    PESSOAL("Pesssoal"),
    COMERCIAL("Comercial");

    private final String description;
}
