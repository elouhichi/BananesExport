package com.kata.BananesExport.Controller;

import com.kata.BananesExport.domain.Commande;
import com.kata.BananesExport.domain.DefaultResponse;
import com.kata.BananesExport.domain.Destinataire;
import com.kata.BananesExport.service.ICommandeService;
import com.kata.BananesExport.service.IDestinataireService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/destinataire")
public class DestinataireController {

    private final IDestinataireService destinataireService;


    @PostMapping("/create")
    public ResponseEntity<Destinataire> createDestinataire(@RequestBody Destinataire destinataireToSave){
        return ResponseEntity.ok(destinataireService.createDestinataire(destinataireToSave));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Destinataire> updateDestinataire(@PathVariable Long id, @RequestBody Destinataire updatedDestinataire){
        return ResponseEntity.ok(destinataireService.updateDestinataire(updatedDestinataire, id));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DefaultResponse> deleteDestinataire(@PathVariable Long id){
        return ResponseEntity.ok(destinataireService.deleteDestinataire(id));
    }

    @GetMapping("/get_all")
    public ResponseEntity<List<Destinataire>> getAllDestinataires(){
        return ResponseEntity.ok(destinataireService.getAllDestinataires());
    }
}
