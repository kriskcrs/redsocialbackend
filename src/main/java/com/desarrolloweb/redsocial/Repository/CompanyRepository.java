package com.desarrolloweb.redsocial.Repository;

import com.desarrolloweb.redsocial.Entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Long> {

    public Company findByIdCompany(long idCompany);
}