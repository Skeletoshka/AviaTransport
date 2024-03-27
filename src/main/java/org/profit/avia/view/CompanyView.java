package org.profit.avia.view;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.Column;

public class CompanyView {

    @Column(name = "company_id")
    @Schema(description = "ИД компании")
    private Integer companyId;

    @Column(name = "company_name")
    @Schema(description = "Наименование компании")
    private String companyName;

    @Column(name = "company_token")
    @Schema(description = "Токен компании")
    private String companyToken;

    public CompanyView() {
    }

    public CompanyView(Integer companyId, String companyName, String companyToken) {
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
