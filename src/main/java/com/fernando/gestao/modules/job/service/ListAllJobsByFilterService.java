package com.fernando.gestao.modules.job.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fernando.gestao.modules.job.model.JobModel;
import com.fernando.gestao.modules.job.repository.JobRepository;

@Service
public class ListAllJobsByFilterService {

    @Autowired
    private JobRepository jobRepository;

    public List<JobModel> execute(String filter){
        

        return this.jobRepository.findByDescriptionContainingIgnoreCase(filter);

    }

}
