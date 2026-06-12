package com.example.demo.service;

import com.example.demo.data.Voiture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StatistiqueTests {

    @Test
    public void testPrixMoyen_AvecVoitures(@Mock Voiture voiture1, @Mock Voiture voiture2) {
        
        StatistiqueImpl statistique = new StatistiqueImpl();

        when(voiture1.getPrix()).thenReturn(10000);
        when(voiture2.getPrix()).thenReturn(20000);

        statistique.ajouter(voiture1);
        statistique.ajouter(voiture2);

        
        Echantillon echantillon = statistique.prixMoyen();

      
    }

    
}