package com.kata.BananesExport.Controller;

import com.kata.BananesExport.domain.Commande;
import com.kata.BananesExport.domain.DefaultResponse;
import com.kata.BananesExport.domain.Destinataire;
import com.kata.BananesExport.service.ICommandeService;

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
@RequestMapping("/commande")
@RequiredArgsConstructor
public class CommandeController {


    private final ICommandeService commandeService;


    @PostMapping("/create")
    public ResponseEntity<Commande> createCommande(@RequestBody Commande commandeToSave){
        return ResponseEntity.ok(commandeService.createCommande(commandeToSave));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Commande> updateCommande(@PathVariable Long id,@RequestBody Commande updatedCommande){
        return ResponseEntity.ok(commandeService.updateCommande(updatedCommande, id));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DefaultResponse> deleteCommande(@PathVariable Long id){
        return ResponseEntity.ok(commandeService.deleteCommande(id));
    }

    @GetMapping("/get_commandes")
    public ResponseEntity<List<Commande>> getCommandeByDestinataire(@RequestBody Destinataire destinataire){
        return ResponseEntity.ok(commandeService.getCommandesByDestinataire(destinataire));
    }
}
