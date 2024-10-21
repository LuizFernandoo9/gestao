package com.fernando.gestao.modules.company.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fernando.gestao.exceptions.UserFoundException;
import com.fernando.gestao.modules.company.dto.AuthCompanyDTO;
import com.fernando.gestao.modules.company.model.CompanyModel;
import com.fernando.gestao.modules.company.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.naming.AuthenticationException;

@Service
public class AuthCompanyService {

    @Value("${security.token.secret}")
    private String secretyKey;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String execute(@RequestBody AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
        var company = this.companyRepository.findByUsername(authCompanyDTO.getUsername()).orElseThrow(() -> {
            throw new UsernameNotFoundException("Company not found");
        });

        var passwordMatches = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());

        if(!passwordMatches){
            throw new AuthenticationException();
        }

        Algorithm algorithm = Algorithm.HMAC256(secretyKey);
        var token = JWT.create().withIssuer("ITEP")
                .withSubject(company.getId().toString())
                .sign(algorithm);
        return token;
    }
}
