package org.profit.avia.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "company")
public class Company {

    @Id
    @Column(name = "company_id", nullable = false)
    private Integer companyId;

    @Column(name = "company_name", nullable = false)
    @Size(max = 255, message = "Поле \"Наименование компании\" не может иметь более {max} символов")
    @NotNull(message = "Поле \"Наименование компании\" не может быть пустым")
    private String companyName;

    @Column(name = "company_token", nullable = false)
    @Size(max = 255, message = "Поле \"Токен компании\" не может иметь более {max} символов")
    @NotNull(message = "Поле \"Токен компании\" не может быть пустым")
    private String companyToken;

    public Company() {
    }

    public Company(Integer companyId, String companyName, String companyToken) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.companyToken = companyToken;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyToken() {
        return companyToken;
    }

    public void setCompanyToken(String companyToken) {
        this.companyToken = companyToken;
    }
}
