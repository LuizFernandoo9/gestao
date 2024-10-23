package com.fernando.gestao.modules.job.model;

import com.fernando.gestao.modules.company.model.CompanyModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity(name = "JOB")
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
