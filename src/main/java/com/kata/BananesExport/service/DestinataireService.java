package com.kata.BananesExport.service;

import org.springframework.stereotype.Service;

import com.kata.BananesExport.domain.DefaultResponse;
import com.kata.BananesExport.domain.Destinataire;
import com.kata.BananesExport.repository.DestinataireRepository;

import java.util.List;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DestinataireService implements IDestinataireService{

    private final DestinataireRepository destinataireRepository;

    @Override
    public Destinataire createDestinataire(Destinataire destinataire) {
        if(checkIfDestinataireIdentiqueExiste(destinataire)){
            throw  new IllegalArgumentException("un destinataire parfaitement identique existe");
        }
        return destinataireRepository.save(destinataire);
    }

    @Override
    public Destinataire updateDestinataire(Destinataire destinataire, Long id) {
        Destinataire destinataireToUpdate = destinataireRepository.findById(id)
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("destinataire à modifier non trouvé");
                });
        destinataireToUpdate.setNom(destinataire.getNom());
        destinataireToUpdate.setAdresse(destinataire.getAdresse());
        destinataireToUpdate.setPays(destinataire.getPays());
        destinataireToUpdate.setVille(destinataire.getVille());
        destinataireToUpdate.setCodePostal(destinataire.getCodePostal());

        if(checkIfDestinataireIdentiqueExiste(destinataireToUpdate)){
            throw  new IllegalArgumentException("un destinataire parfaitement identique existe");
        }
        return destinataireRepository.save(destinataireToUpdate);
    }

    @Override
    public DefaultResponse deleteDestinataire(Long id) {
         destinataireRepository.deleteById(id);
         return new DefaultResponse("ok", "destinataire supprimé avec succées");
    }

    @Override
    public List<Destinataire> getAllDestinataires() {
        return (List<Destinataire>) destinataireRepository.findAll();
    }

    @Override
    public Destinataire finDestinataireById(Long id) {
        return destinataireRepository.findById(id)
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("le destinataire est introuvable");
                });
    }

    private Boolean checkIfDestinataireIdentiqueExiste(Destinataire destinataire){

       return destinataireRepository.findByNomAndAdresseAndVilleAndPaysAndCodePostal(destinataire.getNom(), destinataire.getAdresse(), destinataire.getVille(), destinataire.getPays(), destinataire.getCodePostal())
               .isPresent();
    }
}
