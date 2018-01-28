package com.example.restwsejbdemo.service;

import com.example.restwsejbdemo.domain.Company;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class CompanyManager {

    @PersistenceContext
    EntityManager em;

    public void addCompany(Company company) {
        em.persist(company);
    }

    public void deleteCompany(Company company) {
        em.remove(company);
    }

    public Company getCompany(Long id) {
        return em.find(Company.class, id);
    }

    public Company updateCompany(Company company) {
        return em.merge(company);
    }

    @SuppressWarnings("unchecked")
    public List<Company> getAllCompanies() {
        return em.createNamedQuery("company.getAll").getResultList();
    }

    @SuppressWarnings("unchecked")
    public void deleteAllCompanies() {
        em.createNamedQuery("company.deleteAll").executeUpdate();
    }

}
