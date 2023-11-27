package com.kata.BananesExport.service;

import org.springframework.stereotype.Service;

import com.kata.BananesExport.domain.DefaultResponse;
import com.kata.BananesExport.domain.Destinataire;

import java.util.List;

@Service
public class DestinataireService implements IDestinataireService{

    @Override
    public Destinataire createDestinataire(Destinataire destinataire) {
        return null;
    }

    @Override
    public Destinataire updateDestinataire(Destinataire destinataire, Long id) {
        return null;
    }

    @Override
    public DefaultResponse deleteDestinataire(Long id) {
        return null;
    }

    @Override
    public List<Destinataire> getAllDestinataires() {
        return null;
    }
}
