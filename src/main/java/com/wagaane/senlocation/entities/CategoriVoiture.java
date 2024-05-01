package com.wagaane.senlocation.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "TP_CATEGORIVOITURE")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoriVoiture {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "Cat_id")
  private Long id;
  @Column(name = "Cat_code", unique = true, nullable = false, length = 50)
  private String code;
  @Column(name = "Cat_libelle", nullable = false, length = 150)
  private String libelle;
  @Column(name = "Cat_deleted")
  private boolean deleted = false;
}
