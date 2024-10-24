package com.fernando.gestao.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileCandidateDTOResponse {

    private String username;
    private String name;
    private String description;
    private String email;
    private UUID id;
}
