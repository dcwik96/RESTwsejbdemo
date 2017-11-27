package com.example.restwsdemo.service;

import com.example.restwsdemo.domain.Person;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PersonManager {

    @PersistenceContext
    EntityManager em;

    public void addPerson(Person person) {
        em.persist(person);
    }

    public void deletePerson(Person person) {
        em.remove(person);
    }

    public Person getPerson(Long id) {
        return em.find(Person.class, id);
    }

    public Person updatePerson(Person person) {
        return em.merge(person);
    }
}
