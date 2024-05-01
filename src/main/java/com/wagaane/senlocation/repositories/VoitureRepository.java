package com.wagaane.senlocation.repositories;

import com.wagaane.senlocation.entities.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoitureRepository extends JpaRepository<Voiture, Long> {
}
