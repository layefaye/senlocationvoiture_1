package com.wagaane.senlocation.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TP_CARBURANT")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Carburant {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "Carburant_id")
  private Long id;

  @Column(name = "Carburant_code",unique = true, nullable = false, length = 50)
  private String code;

  @Column(name = "Carburant_libelle", nullable = false, length = 100)
  private String libelle;

  @Column(name = "Carburant_deleted")
  private boolean deleted = false;
}
