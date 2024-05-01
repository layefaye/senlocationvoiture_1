package com.wagaane.senlocation.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TP_COULEUR")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Couleur {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "Couleur_id")
  private Long id;

  @Column(name = "Couleur_code", unique = true, nullable = false, length = 50)
  private String code;

  @Column(name = "Couleur_libelle", nullable = false, length = 100)
  private String libelle;

  @Column(name = "Couleur_deleted")
  private boolean deleted = false;
}
