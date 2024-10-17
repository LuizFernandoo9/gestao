package com.fernando.gestao.modules.job.service;

import com.fernando.gestao.modules.job.model.JobModel;
import com.fernando.gestao.modules.job.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    public JobModel execute(@RequestBody JobModel jobModel){
        return this.jobRepository.save(jobModel);
    }
}
