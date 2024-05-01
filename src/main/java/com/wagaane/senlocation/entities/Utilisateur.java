package com.wagaane.senlocation.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TD_UTILISATEUR")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Utilisateur {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "uti_Id", nullable = false)
  private long id;
  @Column(name = "Uti_Nom", nullable = false, length = 50)
  private String nom;
  @Column(name = "Uti_Prenom", nullable = false, length = 50)
  private String prenom;
  @Column(name = "Uti_Telephone", nullable = false, length = 50)
  private String telephone;
  @Column(name = "Uti_Adresse")
  private String adresse;
  @Column(name = "Uti_Email", nullable = false, unique = true, length = 50)
  @Email
  private String email;
  @OneToOne(cascade = CascadeType.PERSIST)
          @JoinColumn(name = "Uti_compte")
  Compte compte;
}
