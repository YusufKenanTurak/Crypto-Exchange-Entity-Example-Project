package org.webp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InsertTest {

    private EntityManagerFactory factory;
    private EntityManager em;

    @BeforeEach
    public void init() {
        factory = Persistence.createEntityManagerFactory("Hibernate");
        em = factory.createEntityManager();
    }

    @AfterEach
    public void tearDown() {

        em.close();
        factory.close();
    }

    private boolean persistInATransaction(Object obj) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            em.persist(obj);
            tx.commit();
        } catch (Exception e) {
            System.out.println("FAILED TRANSACTION: " + e.toString());
            tx.rollback();
            return false;
        }

        return true;
    }


    @Test
    public void insertEXCHANGE() {

        EXCHANGE EXCHANGE = new EXCHANGE();
        EXCHANGE.setExchangeID(1);

        boolean persisted = persistInATransaction(EXCHANGE);
        assertTrue(persisted);
    }

    @Test
    public void insertCURRENCY() {

        CURRENCY CURRENCY = new CURRENCY();
        CURRENCY.setID(1);

        boolean persisted = persistInATransaction(CURRENCY);
        assertTrue(persisted);
    }
    @Test
    public void insertNETWORK() {

        NETWORK NETWORK = new NETWORK();
        NETWORK.setNetworkID(1);

        boolean persisted = persistInATransaction(NETWORK);
        assertTrue(persisted);
    }

}
