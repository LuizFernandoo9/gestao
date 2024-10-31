package com.fernando.gestao.modules.company.controller;

import com.fernando.gestao.modules.company.model.CompanyModel;
import com.fernando.gestao.modules.company.service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping("/")
    @PreAuthorize("hasRole(COMPANY)")
    public ResponseEntity<Object> create(@Valid @RequestBody CompanyModel companyModel){
        try {
            var company = this.companyService.execute(companyModel);
            return ResponseEntity.status(HttpStatus.OK).body(company);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
