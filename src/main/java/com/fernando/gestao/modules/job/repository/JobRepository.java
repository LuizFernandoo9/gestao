package com.fernando.gestao.modules.job.repository;

import com.fernando.gestao.modules.job.model.JobModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JobRepository extends JpaRepository<JobModel, UUID> {


    List<JobModel> findByDescriptionContainingIgnoreCase(String filter);
}
