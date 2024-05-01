package com.wagaane.senlocation.auth;

import com.wagaane.senlocation.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
  private String nom;
  private String prenom;
  private String telephone;
  private String adresse;
  private String email;
  private String password;
  private Role role;
}
