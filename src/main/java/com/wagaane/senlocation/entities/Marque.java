package com.wagaane.senlocation.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "TP_MARQUE")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Marque {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "Marque_id")
  private Long id;
  @Column(name = "Marque_code", unique = true, nullable = false, length = 50)
  private String code;
  @Column(name = "Marque_libelle", nullable = false, length = 100)
  private String libelle;
  @Column(name = "Marque_deleted")
  private boolean deleted = false;
}
