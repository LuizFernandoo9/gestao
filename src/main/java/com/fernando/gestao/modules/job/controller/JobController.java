package com.fernando.gestao.modules.job.controller;

import com.fernando.gestao.dto.AuthJobDTO;
import com.fernando.gestao.modules.job.model.JobModel;
import com.fernando.gestao.modules.job.service.JobService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/company/job")
public class JobController {

    @Autowired
    private JobService jobService;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody AuthJobDTO authJobDTO, HttpServletRequest request){
        try{
            var companyId = request.getAttribute("company_id");

            var jobModel = JobModel.builder()
                    .benefits(authJobDTO.getBenefits())
                    .description(authJobDTO.getDescription())
                    .level(authJobDTO.getLevel())
                    .idCompany(UUID.fromString(companyId.toString()))
                    .build();

            var job = this.jobService.execute(jobModel);

            return ResponseEntity.status(HttpStatus.OK).body(job);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
