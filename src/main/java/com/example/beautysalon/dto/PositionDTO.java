package com.example.beautysalon.dto;

import com.example.beautysalon.validator.OnlyLetters;
import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PositionDTO implements Serializable {

    private UUID id;

    @OnlyLetters
    private String functionName;

    private String functionDescription;

    private Integer corCode;

}
