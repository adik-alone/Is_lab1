package com.example.service;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Singleton
@Startup
public class DatabaseHealCheck {
    @PersistenceContext(unitName = "myPostgresPU")
    private EntityManager em;

    @PostConstruct
    public void checkDatabaseConnection(){
        try{
            Object o = em.createNativeQuery("select 1").getSingleResult();
            System.out.println("Good");
        } catch (Exception e) {
            System.out.println("Bad: " + e.getMessage());
        }
    }
}
