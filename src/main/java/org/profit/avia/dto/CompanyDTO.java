package org.profit.avia.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.profit.avia.model.Company;

public class CompanyDTO {

    @Schema(description = "ИД компании")
    private Integer companyId;

    @Schema(description = "Наименование компании")
    private String companyName;

    @Schema(description = "Токен компании")
    private String companyToken;

    public CompanyDTO() {
    }

    public CompanyDTO(Integer companyId, String companyName, String companyToken) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.companyToken = companyToken;
    }

    public Company toEntity(){
        return toEntity(new Company());
    }

    public Company toEntity(Company company){
        company.setCompanyId(this.companyId);
        company.setCompanyName(this.companyName);
        company.setCompanyToken(this.companyToken);
        return company;
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
