package com.fernando.gestao.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErroMessageDTO {

    private String message;
    private String field;

}
