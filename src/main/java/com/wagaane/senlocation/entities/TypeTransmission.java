package com.wagaane.senlocation.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TP_TYPE_TRANSMISSION")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TypeTransmission {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "Transmission_id")
  private Long id;

  @Column(name = "Transmission_code",unique = true, nullable = false, length = 50)
  private String code;

  @Column(name = "Transmission_libelle", nullable = false, length = 100)
  private String libelle;

  @Column(name = "Transmission_deleted")
  private boolean deleted = false;

}
