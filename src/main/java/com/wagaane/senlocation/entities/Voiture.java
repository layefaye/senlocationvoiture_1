package com.wagaane.senlocation.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TD_VOITURE")
@Data
public class Voiture {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "voiture_marque", nullable = false)
  private Marque marque;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "voiture_modele", nullable = false)
  private Modele modele;

  @Column(name = "voiture_anneeFabrication")
  private LocalDate anneeFabrication;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "voiture_categori", nullable = false)
  private CategoriVoiture categoriVoiture;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "voiture_couleur", nullable = false)
  private Couleur couleur;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "voiture_typeTransmission", nullable = false)
  private TypeTransmission typeTransmission;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "voiture_carburant", nullable = false)
  private Carburant carburan;

  @Column(name = "voiture_puissanceMoteur", nullable = false)
  private long puissanceMoteur;

  @Column(name = "voiture_nombreCylindres", nullable = false)
  private long nombreCylindres;

  @Column(name = "voiture_prixJour")
  private long prixJour;

  @Column(name = "voiture_estDisponible")
  private boolean estDisponible = true;

  @OneToMany
  private List<File> images;

  @Column(name = "Voiture_deleted")
  private boolean deleted = false;
}
