package com.kata.BananesExport.repository;

import com.kata.BananesExport.domain.Commande;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jdk.jfr.Registered;

@Repository
public interface CommandeRepository extends CrudRepository<Commande, Long> {
}
