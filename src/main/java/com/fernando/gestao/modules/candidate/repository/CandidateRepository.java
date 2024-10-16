package com.fernando.gestao.modules.candidate.repository;

import com.fernando.gestao.modules.candidate.model.CandidateModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CandidateRepository extends JpaRepository<CandidateModel, UUID> {

    Optional<CandidateModel> findByUsernameOrEmail(String username, String email);
}
