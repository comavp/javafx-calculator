package ru.comavp.calculator.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Token {

    private String value;
    private Enum type;
}
