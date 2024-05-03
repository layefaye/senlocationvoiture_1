package com.wagaane.senlocation.repositories;

import com.wagaane.senlocation.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long>, QuerydslPredicateExecutor<Utilisateur> {
}
