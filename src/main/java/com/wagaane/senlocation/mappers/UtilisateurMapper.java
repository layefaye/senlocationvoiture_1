package com.wagaane.senlocation.mappers;

import com.wagaane.senlocation.entities.Utilisateur;
import com.wagaane.senlocation.web.dtos.responses.UtilisateurResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UtilisateurMapper {
  UtilisateurResponse mapToUtilisateurResponse(Utilisateur utilisateur);
}

