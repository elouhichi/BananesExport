package com.kata.BananesExport.repository;

import com.kata.BananesExport.domain.Destinataire;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DestinataireRepository extends CrudRepository<Destinataire, Long> {

    Optional<Destinataire> findByNomAndAdresseAndVilleAndPaysAndCodePostal(String nom, String adresse, String ville, String pays, String codePostal);

}
