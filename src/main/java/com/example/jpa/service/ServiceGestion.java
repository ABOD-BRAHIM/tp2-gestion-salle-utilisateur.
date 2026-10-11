package com.example.jpa.service;

import com.example.jpa.dao.SalleDao;
import com.example.jpa.dao.UtilisateurDoa;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import com.example.jpa.entities.Utilisateur;
import com.example.jpa.entities.Salle;
import java.util.List;
import javax.persistence.Persistence;

public class ServiceGestion {

    private EntityManager em;
    private EntityManagerFactory emf;
    private UtilisateurDoa utilisateurDoa;
    private SalleDao salleDao;

    public ServiceGestion() {
        emf = Persistence.createEntityManagerFactory("persistence.xml");
        em = emf.createEntityManager();
        utilisateurDoa = new UtilisateurDoa(em);
        salleDao = new SalleDao(em);
    }

    public void fermer(){
        em.close();
        emf.close();
    }

    public void ajouterUtilisateur(Utilisateur utilisateur){
        utilisateurDoa.create(utilisateur);
    }

    public void ajouterSalle(Salle salle){
        salleDao.create(salle);
    }

    public void modifierUtilisateur(Utilisateur utilisateur){
        utilisateurDoa.ubdate(utilisateur);
    }

    public void supprimerUtilisateur(Utilisateur utilisateur){
        utilisateurDoa.remove(utilisateur);
    }
    public void supprimerSalle(Salle salle){
        salleDao.remove(salle);
    }
    public void modifierSalle(Salle  salle){
        salleDao.ubdate(salle);
    }

    public List<Utilisateur> getListUtilisateur(){
        return utilisateurDoa.findAll();
    }

    public List<Salle> getListSalle(){
       return salleDao.findAll();
    }
   public Utilisateur getUtilisateurById(int id){
        return utilisateurDoa.find(id);
   }

   public Salle getSalleById(int id){
        return salleDao.find(id);
   }

   public List<Utilisateur> getListUtilisateurByNom(String nomSalle){
        return utilisateurDoa.findByNom(nomSalle);

   }

   public List<Salle> getListSalleByCapacite(int capaciteMin){
        return salleDao.findAll(capaciteMin);

   }

}
