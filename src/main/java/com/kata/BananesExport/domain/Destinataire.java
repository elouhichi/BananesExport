package com.kata.BananesExport.domain;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Builder
@Getter
@Setter
@AllArgsConstructor
@Entity
public class Destinataire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String adresse;
    private String codePostal;
    private String ville;
    private String pays;

    @OneToMany(mappedBy = "destinataire", cascade = CascadeType.ALL)
    private List<Commande> commandes = new ArrayList<>();
}
