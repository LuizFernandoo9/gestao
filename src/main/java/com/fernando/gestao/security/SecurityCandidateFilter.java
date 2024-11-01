package com.fernando.gestao.security;

import com.fernando.gestao.providers.JWTCandidateProviders;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.hibernate.engine.internal.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityCandidateFilter extends OncePerRequestFilter {

    @Autowired
    private JWTCandidateProviders jwtCandidateProviders;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        //SecurityContextHolder.getContext().setAuthentication(null);

        String header = request.getHeader("Authorization");

        if(request.getRequestURI().startsWith("/candidate")){

            if(header != null){
                var token = jwtCandidateProviders.validateToken(header);
    
                if(token == null){
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    return;
                }

    
                request.setAttribute("candidate_id", token.getSubject());
                var roles = token.getClaim("roles").asList(Object.class);

                var grants = roles.stream().map(role -> new SimpleGrantedAuthority("Role_" + role.toString().toUpperCase())).toList();
    
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken
                        (token.getSubject(), null, grants);
                SecurityContextHolder.getContext().setAuthentication(auth);



            }

        }


        filterChain.doFilter(request, response);
    }
}
