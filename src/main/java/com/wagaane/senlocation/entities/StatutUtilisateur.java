package com.wagaane.senlocation.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TP_STATUTUTILISATEUR")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StatutUtilisateur {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @Column(name = "Statut_code", unique = true, nullable = false, length = 50)
  private String code;

  @Column(name = "Statut_libelle", nullable = false, length = 100)
  private String libelle;

  @Column(name = "Statut_deleted")
  private boolean deleted = false;
}
