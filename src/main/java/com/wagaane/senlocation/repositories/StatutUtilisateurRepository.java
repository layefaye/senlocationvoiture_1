package com.wagaane.senlocation.repositories;

import com.wagaane.senlocation.entities.StatutUtilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface StatutUtilisateurRepository extends JpaRepository<StatutUtilisateur, Long>, QuerydslPredicateExecutor<StatutUtilisateur> {
  Optional<StatutUtilisateur> findByCode(String code);
}
