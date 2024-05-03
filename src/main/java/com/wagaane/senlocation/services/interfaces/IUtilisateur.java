package com.wagaane.senlocation.services.interfaces;

import com.wagaane.senlocation.web.dtos.responses.Response;

public interface IUtilisateur {
  Response<Object> findAll(int page, int size, String filter, String email, String telephone, String adresse, String prenom, String nom);

  Response<Object> activerOuDesactiverUtilisateur(long id);
}
