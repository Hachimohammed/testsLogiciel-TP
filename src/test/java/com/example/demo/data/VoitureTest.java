package com.example.demo.data;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class VoitureTest {

    @Test
    void testCreerVoitureConstructeurVide() {
        // Initialisation
        Voiture voiture = new Voiture();
        
        // Action (utilisation des setters)
        voiture.setMarque("Renault");
        voiture.setPrix(15000);
        voiture.setId(1);

        // Vérification
        assertEquals("Renault", voiture.getMarque(), "La marque doit être Renault");
        assertEquals(15000, voiture.getPrix(), "Le prix doit être 15000");
        assertEquals(1, voiture.getId(), "L'id doit être 1");
    }

    @Test
    void testCreerVoitureAvecParametres() {
        
        Voiture voiture = new Voiture("Peugeot", 20000);

       
        assertEquals("Peugeot", voiture.getMarque(), "La marque doit être Peugeot");
        assertEquals(20000, voiture.getPrix(), "Le prix doit être 20000");
    }
}