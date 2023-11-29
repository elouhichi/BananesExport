package com.kata.BananesExport.service;

import org.springframework.stereotype.Service;

import com.kata.BananesExport.domain.Commande;
import com.kata.BananesExport.domain.DefaultResponse;
import com.kata.BananesExport.domain.Destinataire;
import com.kata.BananesExport.repository.CommandeRepository;

import java.time.LocalDate;
import java.util.List;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommandeService implements ICommandeService{

    private final IDestinataireService destinataireService;

    private final CommandeRepository commandeRepository;

    LocalDate currentDate = LocalDate.now();
    LocalDate dateInAWeek = currentDate.plusDays(7);


    @Override
    public Commande createCommande(Commande commande) {

        if(commande.getQuantite() < 0 || commande.getQuantite() > 10000){
            throw new IllegalArgumentException("la quantité doit être comprise entre 0 et 10000");
        }
        if(commande.getDateLivraison().isBefore(dateInAWeek)){
            throw new IllegalArgumentException("La date de livraison doit être, au minimum, une semaine dans le futur par rapport à la date du jour.");
        }
         double calculatePrix = commande.getQuantite() * 2.5;
        Destinataire destinataire = destinataireService.finDestinataireById(commande.getDestinataire().getId());
         Commande commandeToSave = Commande.builder()
                 .prix(calculatePrix)
                 .dateLivraison(commande.getDateLivraison())
                 .quantite(commande.getQuantite())
                 .destinataire(destinataire)
                 .build();

        return commandeRepository.save(commandeToSave);
    }

    @Override
    public Commande updateCommande(Commande commande, Long id) {

        if(commande.getQuantite() < 0 || commande.getQuantite() > 10000){
            throw new IllegalArgumentException("la quantité doit être comprise entre 0 et 10000");
        }
        if(!commande.getDateLivraison().isAfter(dateInAWeek)){
            throw new IllegalArgumentException("La date de livraison doit être, au minimum, une semaine dans le futur par rapport à la date du jour.");
        }
        Commande commandeToUpdate = commandeRepository.findById(id)
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("commande à modifier non trouvé");
                });
         commandeToUpdate.setQuantite(commande.getQuantite());
         commandeToUpdate.setDateLivraison(commande.getDateLivraison());
         commandeToUpdate.setPrix(commande.getQuantite() * 2.5);

        return commandeRepository.save(commandeToUpdate);
    }

    @Override
    public DefaultResponse deleteCommande(Long id) {
         commandeRepository.deleteById(id);
         return new DefaultResponse("ok","commande supprimé avec succées");
    }

    @Override
    public List<Commande> getCommandesByDestinataire(Destinataire destinataire) {
        return commandeRepository.findByDestinataire(destinataire);
    }


}
