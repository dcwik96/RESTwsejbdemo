package com.example.restwsejbdemo.service;

import com.example.restwsejbdemo.domain.PlaceOnShelf;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PlaceOnShelfManager {

    @PersistenceContext
    EntityManager entityManager;

    public void addPlaceOnShelf(PlaceOnShelf pos) {
        entityManager.persist(pos);
    }

    public void deletePlaceOnShelf(PlaceOnShelf pos) {
        entityManager.remove(pos);
    }

    public PlaceOnShelf getPlaceOnShelf(Long id) {
        return entityManager.find(PlaceOnShelf.class, id);
    }

    public PlaceOnShelf updatePlaceOnShelf(PlaceOnShelf pos) {
        return entityManager.merge(pos);
    }

}
