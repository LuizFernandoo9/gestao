package com.fernando.gestao.modules.job.controller;

import com.fernando.gestao.modules.job.model.JobModel;
import com.fernando.gestao.modules.job.service.JobService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private JobService jobService;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody JobModel jobModel){
        try{
            var job = this.jobService.execute(jobModel);
            return ResponseEntity.status(HttpStatus.OK).body(job);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
