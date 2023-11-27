package com.kata.BananesExport.repository;

import com.kata.BananesExport.domain.Commande;
import com.kata.BananesExport.domain.Destinataire;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;


@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CommandeRepositoryTests {

        @Autowired
        CommandeRepository commandeRepository;

        @Test
        void create_commande(){

            //Given
            Destinataire destinataire = Destinataire.builder().nom("dest2")
                    .adresse("adresse2")
                    .codePostal("12345")
                    .ville("ville2")
                    .pays("pays2").build();
            Commande commande = Commande.builder().quantite((short) 10)
                    .destinataire(destinataire)
                    .dateLivraison(LocalDate.now()).build();
            //When
            Commande result = commandeRepository.save(commande);
            //Then
            Assertions.assertNotNull(result.getId());
        }

        @Test
        void update_commande(){
            // Given
            // Création d'une commande
            Destinataire destinataire = Destinataire.builder().nom("dest3")
                    .adresse("adresse3")
                    .codePostal("12345")
                    .ville("ville3")
                    .pays("pays3").build();
            Commande commande = Commande.builder().quantite((short) 20)
                    .destinataire(destinataire)
                    .dateLivraison(LocalDate.now()).build();

            Commande savedCommande = commandeRepository.save(commande);
            // When
            // Mettre à jour les données du destinataire
            savedCommande.setQuantite((short) 30);
            commandeRepository.save(savedCommande);
            // Then
            Commande updatedCommande = commandeRepository.findById(savedCommande.getId()).orElse(null);
            Assertions.assertNotNull(updatedCommande);
            Assertions.assertEquals( 30, updatedCommande.getQuantite());
        }

        @Test
        void delete_destinataire(){
            // Given
            // Création d'une commande
            Destinataire destinataire = Destinataire.builder().nom("dest")
                    .adresse("adresse 4")
                    .codePostal("12345")
                    .ville("ville 4")
                    .pays("pays 4").build();
            Commande commande = Commande.builder().quantite((short) 50)
                    .destinataire(destinataire)
                    .dateLivraison(LocalDate.now()).build();

            Commande savedCommande = commandeRepository.save(commande);

            // When
            commandeRepository.deleteById(savedCommande.getId());

            Assertions.assertFalse(commandeRepository.existsById(savedCommande.getId()));
        }

        @Test
        void get_all_destinataire(){
            // Given
            // Ajouter plusieurs destinataires
            Destinataire destinataire = Destinataire.builder().nom("dest5")
                    .adresse("adresse5")
                    .codePostal("67891")
                    .ville("ville5")
                    .pays("pays5").build();
            Commande commande1 = Commande.builder().quantite((short) 50)
                    .destinataire(destinataire)
                    .dateLivraison(LocalDate.now()).build();

            commandeRepository.save(commande1);
            Commande commande2 = Commande.builder().quantite((short) 50)
                    .destinataire(destinataire)
                    .dateLivraison(LocalDate.now()).build();

            commandeRepository.save(commande2);

            // When
            List<Commande> commandes = (List<Commande>) commandeRepository.findAll();
            // Then
            Assertions.assertNotNull(commandes);
            Assertions.assertEquals(2, commandes.size());
        }
    }

