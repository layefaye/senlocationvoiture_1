package com.wagaane.senlocation.web.dtos.responses;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UtilisateurResponse {
  private Long id;
  private String nom;
  private String prenom;
  private String email;
  private String telephone;
  private String adresse;
}
