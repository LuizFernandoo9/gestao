package com.fernando.gestao.modules.candidate.controller;


import com.fernando.gestao.dto.ProfileCandidateDTOResponse;
import com.fernando.gestao.modules.candidate.model.CandidateModel;
import com.fernando.gestao.modules.candidate.service.CreateCandidateService;
import com.fernando.gestao.modules.job.model.JobModel;
import com.fernando.gestao.modules.job.service.ListAllJobsByFilterService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/candidate")
@Tag(name = "candidato", description = "informações do candidato")
public class CandidateController {

    @Autowired
    private CreateCandidateService createCandidateService;

    @Autowired
    private ListAllJobsByFilterService listAllJobsByFilterService;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CandidateModel candidateModel){
        try {
            var candidate = this.createCandidateService.execute(candidateModel);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(candidate);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('CANDIDATE')")
    public ResponseEntity<Object> get(HttpServletRequest request){
        var candidateId = request.getAttribute("candidate_id");

        try{
            var candidate = this.createCandidateService.profile(UUID.fromString(candidateId.toString()));
            return ResponseEntity.status(HttpStatus.OK).body(candidate);
        }catch (Exception e){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/job")
    @PreAuthorize("hasRole('CANDIDATE')")
    
    @Operation(summary = "Lista de vagas para o candidato", description = "função por listar todas as vagas disponíveis no filtro")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(array = @ArraySchema(schema = @Schema(implementation = JobModel.class)))
        })
    })
    @SecurityRequirement(name = "jwt_auth")
    public List<JobModel> findJobByFilter(@RequestParam String filter){
        return this.listAllJobsByFilterService.execute(filter);
    }

}
