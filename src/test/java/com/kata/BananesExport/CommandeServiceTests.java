package com.kata.BananesExport;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.kata.BananesExport.domain.Commande;
import com.kata.BananesExport.domain.DefaultResponse;
import com.kata.BananesExport.domain.Destinataire;
import com.kata.BananesExport.repository.CommandeRepository;
import com.kata.BananesExport.repository.DestinataireRepository;
import com.kata.BananesExport.service.ICommandeService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class CommandeServiceTests extends BananesExportApplicationTests {

    @Autowired
    ICommandeService commandeService;

    @MockBean
    CommandeRepository commandeRepository;

    @MockBean
    DestinataireRepository destinataireRepository;

    @Test
    void create_commande(){
        Destinataire destinataire = Destinataire.builder()
                .id(100L)
                .nom("dest")
                .adresse("adresse 3")
                .codePostal("12345")
                .ville("ville 5")
                .pays("pays 0").build();

        Commande commande = Commande.builder()
                .id(22L)
                .destinataire(destinataire)
                .quantite((short) 50)
                .prix(125)
                .dateLivraison(LocalDate.now().plusDays(10)).build();
        when(commandeRepository.save(any(Commande.class))).thenReturn(commande);
        when(destinataireRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(destinataire));
        Commande savedCommande = commandeService.createCommande(commande);

        // Then
        Assertions.assertNotNull(savedCommande);
        Assertions.assertTrue(commande.getQuantite() > 0 && commande.getQuantite() < 10000);
        Assertions.assertEquals(commande.getQuantite() * 2.5, savedCommande.getPrix());
        LocalDate currentDate = LocalDate.now();
        LocalDate dateInAWeek = currentDate.plusDays(6);
        Assertions.assertTrue(savedCommande.getDateLivraison().isAfter(dateInAWeek));
    }

     @Test
    void update_commande(){
         Destinataire destinataire = Destinataire.builder().nom("dest")
                 .adresse("adresse 3")
                 .codePostal("12345")
                 .ville("ville 5")
                 .pays("pays 0").build();
        Commande commande = Commande.builder()
                .id(1L)
                .destinataire(destinataire)
                .quantite((short) 10)
                .dateLivraison(LocalDate.now().plusDays(8))
                .build();
         when(commandeRepository.save(any(Commande.class))).thenReturn(commande);
         when(commandeRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(commande));
         Commande commandeToUpdate = Commande.builder()
                 .quantite((short) 20)
                 .dateLivraison(LocalDate.now().plusDays(20))
                 .build();
         Commande updatedCommande = commandeService.updateCommande(commandeToUpdate, commande.getId());
        Assertions.assertEquals(20, updatedCommande.getQuantite());
    }

    @Test
    void
    delete_commande(){
        // Given
        Commande commande = Commande.builder()
                .id(10L)
                .quantite((short) 50)
                .dateLivraison(LocalDate.now().plusDays(10)).build();
       //When
        doNothing().when(commandeRepository).deleteById(any(Long.class));

        DefaultResponse response =commandeService.deleteCommande(commande.getId());
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
        List<Commande> commandes = new ArrayList<>();
        commandes.add(commande);
        // When
        when(commandeRepository.findByDestinataire(destinataire)).thenReturn(commandes);
         commandes = commandeService.getCommandesByDestinataire(destinataire);
        // Then
        Assertions.assertNotNull(commandes);
    }
}
