package com.kata.BananesExport.repository;

import com.kata.BananesExport.domain.Commande;
import com.kata.BananesExport.domain.Destinataire;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CommandeRepository extends CrudRepository<Commande, Long> {

    List<Commande> findByDestinataire(Destinataire destinataire);
}
