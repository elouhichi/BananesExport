package com.kata.BananesExport.service;

import com.kata.BananesExport.domain.Commande;
import com.kata.BananesExport.domain.DefaultResponse;
import com.kata.BananesExport.domain.Destinataire;

import java.util.List;

public interface ICommandeService {

    Commande createCommande(Commande commandeService);

    Commande updateCommande(Commande commande, Long id);

    DefaultResponse deleteCommande(Long id);

    List<Commande> getCommandesByDestinataire(Destinataire destinataire);

    }
