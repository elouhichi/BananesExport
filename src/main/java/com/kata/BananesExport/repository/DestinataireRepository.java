package com.kata.BananesExport.repository;

import com.kata.BananesExport.domain.Destinataire;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinataireRepository extends CrudRepository<Destinataire, Long> {
}
