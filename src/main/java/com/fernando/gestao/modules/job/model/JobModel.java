package com.fernando.gestao.modules.job.model;

import com.fernando.gestao.modules.company.model.CompanyModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "JOB")
public class JobModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String description;

    @NotBlank(message = "Campo Obrigatório")
    private String level;
    private String benefits;

    @Column(name = "company_id")
    private UUID idCompany;

    @ManyToOne()
    @JoinColumn(name = "company_id", insertable = false, updatable = false)
    private CompanyModel companyModel;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
