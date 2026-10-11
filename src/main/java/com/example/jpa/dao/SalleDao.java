package com.example.jpa.dao;

import com.example.jpa.entities.Salle;

import javax.persistence.EntityManager;
import java.util.List;

public class SalleDao extends GenericDao<Salle> {

    public SalleDao (EntityManager em) {
        super(Salle.class, em);
    }

     public List<Salle> findAll(int CapaciteMin) {
         return em.createQuery("SELECT s FROM Salle s WHERE capacite >: CapaciteMin",Salle.class)
                 .setParameter("CapaciteMin", CapaciteMin)
                 .getResultList();
     }
}
