package com.kata.BananesExport;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDate;

import java.util.List;

import com.kata.BananesExport.domain.Commande;
import com.kata.BananesExport.domain.DefaultResponse;
import com.kata.BananesExport.domain.Destinataire;
import com.kata.BananesExport.service.ICommandeService;

public class CommandeServiceTests extends BananesExportApplicationTests {

    @Autowired
    ICommandeService commandeService;

    @Test
    void create_commande(){
        Destinataire destinataire = Destinataire.builder().nom("dest")
                .adresse("adresse 4")
                .codePostal("12345")
                .ville("ville 4")
                .pays("pays 4").build();
        Commande commande = Commande.builder().quantite((short) 50)
                .destinataire(destinataire)
                .dateLivraison(LocalDate.now().plusDays(10)).build();
        Commande savedCommande = commandeService.createCommande(commande);
        // Then
        Assertions.assertNotNull(savedCommande);
        Assertions.assertTrue(commande.getQuantite() > 0 && commande.getQuantite() < 10000);
        Assertions.assertEquals(BigDecimal.valueOf(commande.getQuantite() * 2.5), savedCommande.getPrix());
        LocalDate currentDate = LocalDate.now();
        LocalDate dateInAWeek = currentDate.plusDays(6);
        Assertions.assertTrue(savedCommande.getDateLivraison().isAfter(dateInAWeek));
    }

     @Test
    void update_commande(){
        Destinataire destinataire = Destinataire.builder().nom("dest")
                .adresse("adresse 5")
                .codePostal("12345")
                .ville("ville 5")
                .pays("pays 6").build();
        Commande commande = Commande.builder().quantite((short) 10)
                .destinataire(destinataire)
                .dateLivraison(LocalDate.now().plusDays(8)).build();
         Commande savedCommande = commandeService.createCommande(commande);
         Commande commandeToUpdate = Commande.builder().quantite((short) 20)
                 .destinataire(destinataire)
                 .dateLivraison(LocalDate.now().plusDays(20)).build();
         Commande updatedCommande = commandeService.updateCommande(commandeToUpdate, savedCommande.getIdCommande());
        Assertions.assertEquals(20, updatedCommande.getQuantite());
    }

    @Test
    void delete_commande(){
        // Given
        Destinataire destinataire = Destinataire.builder().nom("dest")
                .adresse("adresse 6")
                .codePostal("12345")
                .ville("ville 5")
                .pays("pays 9").build();
        Commande commande = Commande.builder().quantite((short) 50)
                .destinataire(destinataire)
                .dateLivraison(LocalDate.now().plusDays(10)).build();
        Commande savedCommande = commandeService.createCommande(commande);
       //When
        DefaultResponse response =commandeService.deleteCommande(savedCommande.getIdCommande());
        Assertions.assertNotNull(response);
        Assertions.assertEquals("ok", response.status());
    }

    @Test
    void get_commande_by_destinataire(){
       // Given
        Destinataire destinataire = Destinataire.builder().nom("dest")
                .adresse("adresse 3")
                .codePostal("12345")
                .ville("ville 5")
                .pays("pays 0").build();
        Commande commande = Commande.builder().quantite((short) 50)
                .destinataire(destinataire)
                .dateLivraison(LocalDate.now().plusDays(10)).build();
        commandeService.createCommande(commande);

        // When
        List<Commande> commandes = commandeService.getCommandesByDestinataire(destinataire);
        // Then
        Assertions.assertNotNull(commandes);
    }
}
