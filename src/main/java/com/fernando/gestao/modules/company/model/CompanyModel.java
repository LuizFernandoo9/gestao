package com.fernando.gestao.modules.company.model;

import com.fernando.gestao.modules.job.model.JobModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;


@Data
@Entity(name = "company")
public class CompanyModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;

    @NotBlank()
    @Pattern(regexp = "\\S+", message = "o campo [username] não deve conter espaços")
    private String username;


    @Email(message = "Deve conter um email válido")
    private String email;

    @Column(length = 255)
    @Length(min = 8, max = 100, message = "A senha deve conter entre 8 e 100 caracteres")
    private String password;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
