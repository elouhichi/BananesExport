package com.kata.BananesExport.repository;

import com.kata.BananesExport.domain.Destinataire;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.List;

@DataJdbcTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class DestinataireRepositoryTests {

    @Autowired
    DestinataireRepository destinataireRepository;

    @Test
    void create_destinataire(){
        //Given
        Destinataire destinataire = Destinataire.builder().nom("dest1")
                .adresse("adresse1")
                .codePostal("12345")
                .ville("ville1")
                .pays("pays1").build();
        //When
        Destinataire result = destinataireRepository.save(destinataire);
        //Then
        Assertions.assertNotNull(result.getId());
    }

    @Test
    void update_destinataire(){
        // Given
        // Création d'un destinataire
       Destinataire destinataire = Destinataire.builder().nom("dest2")
               .adresse("adresse2")
               .codePostal("12345")
               .ville("ville2")
               .pays("pays2").build();

        Destinataire savedDestinataire = destinataireRepository.save(destinataire);
         // When
        // Mettre à jour les données du destinataire
        savedDestinataire.setNom("nouveau destinataire");
        destinataireRepository.save(savedDestinataire);
        // Then
        Destinataire updatedDestinataire = destinataireRepository.findById(savedDestinataire.getId()).orElse(null);
        Assertions.assertNotNull(updatedDestinataire);
        Assertions.assertEquals("nouveau destinataire", updatedDestinataire.getNom());
    }

    @Test
    void delete_destinataire(){
        // Given
        // Création d'un destinataire
        Destinataire destinataire = Destinataire.builder().nom("dest3")
                .adresse("adresse3")
                .codePostal("12345")
                .ville("ville3")
                .pays("pays3").build();

        Destinataire savedDestinataire = destinataireRepository.save(destinataire);

        // When
        destinataireRepository.deleteById(savedDestinataire.getId());

        Assertions.assertFalse(destinataireRepository.existsById(savedDestinataire.getId()));
    }

    @Test
    void get_all_destinataire(){
        // Given
       // Ajouter plusieurs destinataires
        Destinataire destinataire1 = Destinataire.builder().nom("dest4")
                .adresse("adresse4")
                .codePostal("67891")
                .ville("ville4")
                .pays("pays4").build();

        Destinataire destinataire2 = Destinataire.builder().nom("dest5")
                .adresse("adresse5")
                .codePostal("67891")
                .ville("ville5")
                .pays("pays5").build();

        destinataireRepository.save(destinataire1);
        destinataireRepository.save(destinataire2);

        // When
        List<Destinataire> destinataires = (List<Destinataire>) destinataireRepository.findAll();

        // Then
        Assertions.assertNotNull(destinataires);

        Assertions.assertEquals(2, destinataires.size());
    }
}
