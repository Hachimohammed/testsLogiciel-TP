package com.example.demo.web;

import com.example.demo.data.Voiture;
import com.example.demo.service.Echantillon;
import com.example.demo.service.StatistiqueImpl;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class WebTests {

    @MockBean
    StatistiqueImpl statistiqueImpl;

    @Autowired
    MockMvc mockMvc;

    // 1. Test de la création d'une voiture (Requête POST)
    @Test
    public void testCreerVoiture() throws Exception {
        String jsonVoiture = "{\"marque\":\"f\",\"prix\":100}";

        mockMvc.perform(post("/voiture")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonVoiture))
                .andExpect(status().isOk());

        verify(statistiqueImpl, times(1)).ajouter(any(Voiture.class));
    }

    // 2. Test de la récupération des statistiques (Requête GET - Succès)
    @Test
    public void testGetStatistiques() throws Exception {
        Echantillon fauxEchantillon = new Echantillon(5, 20000);
        when(statistiqueImpl.prixMoyen()).thenReturn(fauxEchantillon);

        mockMvc.perform(get("/statistique"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombreDeVoitures").value(5))
                .andExpect(jsonPath("$.prixMoyen").value(20000));
    }

    // 3. Test de la récupération des statistiques (Requête GET - Échec / Liste vide)
    @Test
    public void testGetStatistiques_SansVoiture() throws Exception {
        // Préparation : On force le mock à lancer une ArithmeticException (comme si la liste était vide)
        when(statistiqueImpl.prixMoyen()).thenThrow(new ArithmeticException());

       
    }
}