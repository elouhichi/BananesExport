package com.kata.BananesExport;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


import com.kata.BananesExport.domain.Commande;
import com.kata.BananesExport.domain.DefaultResponse;
import com.kata.BananesExport.domain.Destinataire;

import com.kata.BananesExport.service.IDestinataireService;

import java.time.LocalDate;
import java.util.List;


public class DestinataireServiceTests extends BananesExportApplicationTests{

    @Autowired
    IDestinataireService destinataireService;

    @Test
    void create_destinataire() {
        // Given
        Destinataire destinataire = Destinataire.builder().nom("dest")
                .adresse("adresse 4")
                .codePostal("12345")
                .ville("ville 4")
                .pays("pays 4").build();
        // When
        Destinataire savedDestinataire = destinataireService.createDestinataire(destinataire);
        // Then
        Assertions.assertNotNull(savedDestinataire);
    }
    @Test
    void update_destinataire(){
        Destinataire destinataire = Destinataire.builder().nom("dest")
                .adresse("adresse 5")
                .codePostal("13579")
                .ville("ville 5")
                .pays("pays").build();
        Destinataire savedDestinataire = destinataireService.createDestinataire(destinataire);
        Destinataire destinataireToUpdate = Destinataire.builder().nom("dest")
                .adresse("adresse 5")
                .codePostal("13579")
                .ville("ville updated")
                .pays("pays").build();
        Destinataire updatedDestinataire = destinataireService.updateDestinataire(destinataireToUpdate, savedDestinataire.getIdDestinataire());
        Assertions.assertEquals("ville updated", updatedDestinataire.getVille());
    }

    @Test
    void delete_destinataire(){
        // Given
        Destinataire destinataire = Destinataire.builder().nom("dest")
                .adresse("adresse11")
                .codePostal("12345")
                .ville("ville")
                .pays("pays").build();
        Destinataire savedDestinataire = destinataireService.createDestinataire(destinataire);

        //When
        DefaultResponse response =destinataireService.deleteDestinataire(savedDestinataire.getIdDestinataire());
        Assertions.assertNotNull(response);
        Assertions.assertEquals("ok", response.status());
    }

    @Test
    void get_all_destinataires(){
        // Given
        Destinataire destinataire = Destinataire.builder().nom("dest")
                .adresse("adresse 3")
                .codePostal("98765")
                .ville("ville10")
                .pays("pays 0").build();
        destinataireService.createDestinataire(destinataire);

        // When
        List<Destinataire> destinataires = destinataireService.getAllDestinataires();
        // Then
        Assertions.assertNotNull(destinataires);
    }
}

