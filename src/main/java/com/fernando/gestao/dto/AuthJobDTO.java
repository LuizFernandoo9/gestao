package com.fernando.gestao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

@Data
@AllArgsConstructor
public class AuthJobDTO {

    @Schema(example = "Vaga para Desenvolvedor Júnior", requiredMode = RequiredMode.REQUIRED)
    private String description;
    @Schema(example = "Vaga para Desenvolvedor Júnior", requiredMode = RequiredMode.REQUIRED)
    private String benefits;
    @Schema(example = "Vaga para Desenvolvedor Júnior", requiredMode = RequiredMode.REQUIRED)
    private String level;

}
