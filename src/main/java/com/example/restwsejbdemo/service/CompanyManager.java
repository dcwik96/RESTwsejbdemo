package com.example.restwsdemo.service;

import com.example.restwsdemo.domain.Company;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

}
