package com.fernando.gestao.modules.company.repository;

import com.fernando.gestao.modules.company.model.CompanyModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CompanyRepository extends JpaRepository<CompanyModel, UUID> {

    Optional<CompanyModel> findByUsernameOrEmail(String username, String email);
}
