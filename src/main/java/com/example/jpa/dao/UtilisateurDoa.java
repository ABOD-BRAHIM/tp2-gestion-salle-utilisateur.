package com.example.jpa.dao;

import com.example.jpa.entities.Utilisateur;

import javax.persistence.EntityManager;
import java.util.List;

public class UtilisateurDoa extends GenericDao<Utilisateur>{

    public UtilisateurDoa(EntityManager em){
        super(Utilisateur.class, em);
    }

    public List<Utilisateur> findByNom(String nomSalle){
        return em.createQuery("select u from Utilisateur u where u.salle.nom = :nomSalle", Utilisateur.class)
                .setParameter("nomSalle", nomSalle)
                .getResultList();
    }
}
