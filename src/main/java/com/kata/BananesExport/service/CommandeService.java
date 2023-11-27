package com.kata.BananesExport.service;

import org.springframework.stereotype.Service;

import com.kata.BananesExport.domain.Commande;
import com.kata.BananesExport.domain.DefaultResponse;
import com.kata.BananesExport.domain.Destinataire;

import java.util.List;

@Service
public class CommandeService implements ICommandeService{

    @Override
    public Commande createCommande(Commande commandeService) {
        return null;
    }

    @Override
    public Commande updateCommande(Commande commande, Long id) {
        return null;
    }

    @Override
    public DefaultResponse deleteCommande(Long id) {
        return null;
    }

    @Override
    public List<Commande> getCommandesByDestinataire(Destinataire destinataire) {
        return null;
    }


}
