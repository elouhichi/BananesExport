package com.kata.BananesExport.service;

import com.kata.BananesExport.domain.DefaultResponse;
import com.kata.BananesExport.domain.Destinataire;

import java.util.List;

public interface IDestinataireService {

     Destinataire createDestinataire(Destinataire destinataire);

     Destinataire updateDestinataire(Destinataire destinataire, Long id );

     DefaultResponse deleteDestinataire(Long id);

     List<Destinataire> getAllDestinataires();


}
