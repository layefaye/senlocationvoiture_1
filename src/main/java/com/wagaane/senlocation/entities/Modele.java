package com.wagaane.senlocation.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "TP_MODELE")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Modele {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "Modele_id")
  private Long id;
  @Column(name = "Modele_code", unique = true, nullable = false, length = 50)
  private String code;
  @Column(name = "Modele_libelle", nullable = false, length = 100)
  private String libelle;
  @Column(name = "Modele_deleted")
  private boolean deleted = false;
  @ManyToOne
  @JoinColumn(name = "Modele_marque")
  private Marque marques;
}
