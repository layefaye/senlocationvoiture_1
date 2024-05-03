package com.wagaane.senlocation.web.controllers;

import com.wagaane.senlocation.services.interfaces.IUtilisateur;
import com.wagaane.senlocation.web.dtos.responses.Response;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/senlocationvoiture/utilisateur")
@RequiredArgsConstructor
public class UtilisateurController {
  private final IUtilisateur iUtilisateur;


  @GetMapping("/all")
  @Operation(summary = "Listes des utilisateurs de la plateforme")
  public ResponseEntity<Response<Object>> findAll(
          @RequestParam(name = "page", defaultValue = "0") int page,
          @RequestParam(name = "size", defaultValue = "10") int size,
          @RequestParam(name = "filter", defaultValue = "") String filter,
          @RequestParam(name = "email", defaultValue = "") String email,
          @RequestParam(name = "telephone", defaultValue = "") String telephone,
          @RequestParam(name = "adresse", defaultValue = "") String adresse,
          @RequestParam(name = "prenom", defaultValue = "") String prenom,
          @RequestParam(name = "nom", defaultValue = "") String nom

          )
          {
    return ResponseEntity.ok(iUtilisateur.findAll(page, size, filter,email, telephone, adresse, prenom, nom));
  }


  @GetMapping("/{id}")
  @Operation(summary = "Activer un utilisateur")
  public ResponseEntity<Response<Object>> activerUtilisateur(@PathVariable long id){
    return ResponseEntity.ok(iUtilisateur.activerOuDesactiverUtilisateur(id));
  }

}
