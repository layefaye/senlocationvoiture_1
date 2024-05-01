package com.wagaane.senlocation.repositories;

import com.wagaane.senlocation.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompteRepository extends JpaRepository<Compte, Long> {
  Optional<Compte> findByEmail(String userEmail);
}
