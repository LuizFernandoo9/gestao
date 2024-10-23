package com.fernando.gestao.modules.company.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
public class AuthJobDTO {

    private String description;
    private String benefits;
    private String level;

}
