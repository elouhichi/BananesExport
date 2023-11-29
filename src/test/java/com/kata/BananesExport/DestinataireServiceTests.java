package com.kata.BananesExport;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;


import com.kata.BananesExport.domain.DefaultResponse;
import com.kata.BananesExport.domain.Destinataire;

import com.kata.BananesExport.repository.DestinataireRepository;
import com.kata.BananesExport.service.IDestinataireService;


import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;


public class DestinataireServiceTests extends BananesExportApplicationTests{

    @Autowired
    IDestinataireService destinataireService;

    @MockBean
    DestinataireRepository destinataireRepository;

    @Test
    void create_destinataire() {
        // Given
        Destinataire destinataire = Destinataire.builder().nom("dest")
                .adresse("adresse 4")
                .codePostal("12345")
                .ville("ville 4")
                .pays("pays 4").build();
        // When
        when(destinataireRepository.save(any(Destinataire.class))).thenReturn(destinataire);
        Destinataire savedDestinataire = destinataireService.createDestinataire(destinataire);
        // Then
        Assertions.assertNotNull(savedDestinataire);
    }
    @Test
    void update_destinataire(){
        Destinataire destinataireToUpdate = Destinataire.builder()
                .id(11L)
                .nom("dest")
                .adresse("adresse 5")
                .codePostal("13579")
                .ville("ville updated")
                .pays("pays").build();
        //When
        when(destinataireRepository.save(any(Destinataire.class))).thenReturn(destinataireToUpdate);
        when(destinataireRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(destinataireToUpdate));
        Destinataire updatedDestinataire = destinataireService.updateDestinataire(destinataireToUpdate, destinataireToUpdate.getId());
        Assertions.assertEquals("ville updated", updatedDestinataire.getVille());
    }

    @Test
    void delete_destinataire(){
        // Given

        //When
        doNothing().when(destinataireRepository).deleteById(any(Long.class));
        DefaultResponse response =destinataireService.deleteDestinataire(50L);
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

