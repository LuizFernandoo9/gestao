package com.fernando.gestao.modules.company.service;

import com.fernando.gestao.exceptions.UserFoundException;
import com.fernando.gestao.modules.company.model.CompanyModel;
import com.fernando.gestao.modules.company.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CompanyModel execute(@RequestBody CompanyModel companyModel){
       this.companyRepository.findByUsernameOrEmail(companyModel.getUsername(), companyModel.getEmail())
                .ifPresent((company) -> {
                    throw new UserFoundException();
                });

                var password = passwordEncoder.encode(companyModel.getPassword());
                companyModel.setPassword(password);

       return this.companyRepository.save(companyModel);


    }
}
