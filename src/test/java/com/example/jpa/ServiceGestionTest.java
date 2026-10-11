package com.example.jpa;

import com.example.jpa.entities.Utilisateur;
import com.example.jpa.entities.Salle;
import com.example.jpa.service.ServiceGestion;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ServiceGestionTest{

    private ServiceGestion serviceGestion;

    @Before
    public void setUp() {
        serviceGestion = new ServiceGestion();
    }

    @After
    public void tearDown() {
        serviceGestion.fermer();
    }

    @Test
    public void testRechercheEtCreationSalle() {
        Salle salle = new Salle("Salle Test", 20);
        serviceGestion.ajouterSalle(salle);

        List<Salle> salles = serviceGestion.getListSalle();
        assertFalse(salles.isEmpty());
        assertEquals("Salle Test",salles.get(0).getNom());

    }

    @Test
    public void testRechercheEtCreationUtilisateuravecSalle() {
         Salle salle = new Salle("Salle A", 20);
         serviceGestion.ajouterSalle(salle);


        Utilisateur utilisateur = new Utilisateur("Utilisateur Test","utilisateurtest@gmail.com",salle);
        serviceGestion.ajouterUtilisateur(utilisateur);

        List<Utilisateur > utilisateurs = serviceGestion.getListUtilisateur();
        assertFalse(utilisateurs.isEmpty());
        assertEquals("utilisateurtest@gmail.com",utilisateurs.get(0).getEmail());
        assertEquals("Salle A",utilisateurs.get(0).getSalle().getNom());
    }

    @Test
    public void testRechercheUtilisateursParSalle() {
        Salle salle = new Salle("Salle C", 30);
        serviceGestion.ajouterSalle(salle);

        Utilisateur u1 = new Utilisateur("Bob", "bob@example.com", salle);
        Utilisateur u2 = new Utilisateur("Carol", "carol@example.com", salle);
        serviceGestion.ajouterUtilisateur(u1);
        serviceGestion.ajouterUtilisateur(u2);

        List<Utilisateur> utilisateurs = serviceGestion.getListUtilisateurByNom("Salle C");
        assertEquals(2, utilisateurs.size());
    }
}
