package com.wagaane.senlocation.services.implementations;

import com.querydsl.core.BooleanBuilder;
import com.wagaane.senlocation.entities.QUtilisateur;
import com.wagaane.senlocation.entities.Utilisateur;
import com.wagaane.senlocation.mappers.UtilisateurMapper;
import com.wagaane.senlocation.repositories.StatutUtilisateurRepository;
import com.wagaane.senlocation.repositories.UtilisateurRepository;
import com.wagaane.senlocation.services.interfaces.IUtilisateur;
import com.wagaane.senlocation.web.dtos.responses.Response;
import com.wagaane.senlocation.web.dtos.responses.UtilisateurResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServiceUtilisateur implements IUtilisateur {
  private final UtilisateurRepository utilisateurRepository;
  private final UtilisateurMapper utilisateurMapper;
  private final StatutUtilisateurRepository statutUtilisateurRepository;


  /**
   * cette methode permet de recuperer la liste des utilisateurs
   * @param page
   * @param size
   * @param filter
   * @param email
   * @param telephone
   * @param adresse
   * @param prenom
   * @param nom
   * @return
   */
  @Override
  public Response<Object> findAll(int page, int size, String filter, String email, String telephone, String adresse, String prenom, String nom) {
    Page<UtilisateurResponse> utilisateurResponses;
    BooleanBuilder builder = new BooleanBuilder();


    builder.and(
            QUtilisateur.utilisateur.deleted.isFalse()
    );

    if (StringUtils.isNoneBlank(filter)){
      builder.andAnyOf(
              QUtilisateur.utilisateur.email.containsIgnoreCase(filter),
              QUtilisateur.utilisateur.adresse.containsIgnoreCase(filter),
              QUtilisateur.utilisateur.nom.containsIgnoreCase(filter),
              QUtilisateur.utilisateur.prenom.containsIgnoreCase(filter),
              QUtilisateur.utilisateur.telephone.containsIgnoreCase(filter),
              QUtilisateur.utilisateur.adresse.containsIgnoreCase(filter)

      );
    }

    // recherche par email
    if (StringUtils.isNoneBlank(email)){
      builder.and(
              QUtilisateur.utilisateur.email.containsIgnoreCase(email)
      );
    }

    // recherche  par telephone
    if (StringUtils.isNoneBlank(telephone)){
      builder.and(
              QUtilisateur.utilisateur.telephone.containsIgnoreCase(telephone)
      );
    }

    // recherche par adresse
    if (StringUtils.isNoneBlank(adresse)){
      builder.and(
              QUtilisateur.utilisateur.adresse.containsIgnoreCase(adresse)
      );
    }

    // recherche par prenom
    if (StringUtils.isNoneBlank(prenom)){
      builder.and(
              QUtilisateur.utilisateur.prenom.containsIgnoreCase(prenom)
      );
    }

    // recherche par nom
    if (StringUtils.isNoneBlank(nom)){
      builder.and(
              QUtilisateur.utilisateur.nom.containsIgnoreCase(nom)
      );
    }

    utilisateurResponses = Objects.nonNull(builder.getValue()) ?
            utilisateurRepository.findAll(builder.getValue(), PageRequest.of(page,size, Sort.by(Sort.Direction.DESC, "id"))).map(utilisateurMapper::mapToUtilisateurResponse)
            :
            utilisateurRepository.findAll(PageRequest.of(page,size, Sort.by(Sort.Direction.DESC, "id"))).map(utilisateurMapper::mapToUtilisateurResponse);
    Response.PageMetadata pageMetadata = Response.PageMetadata.builder()
            .size(utilisateurResponses.getSize())
            .number(utilisateurResponses.getNumber())
            .totalElements(utilisateurResponses.getTotalElements())
            .totalPages(utilisateurResponses.getTotalPages())
            .build();
    return Response.ok().setPayload(utilisateurResponses.getContent()).setMetadata(pageMetadata).setMessage("Liste des Utilisateur");
  }

  /**
   * activer ou desactiver utilisateur
   * @param id
   * @return
   */
  @Override
  public Response<Object> activerOuDesactiverUtilisateur(long id) {
    Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findById(id);
    if (optionalUtilisateur.isEmpty())
      return Response.exception().setMessage("Cette utilisateur n'existe pas");
    Utilisateur utilisateur = optionalUtilisateur.get();
    if (utilisateur.getStatutUtilisateur().getCode().equals("ACTIF"))
      utilisateur.setStatutUtilisateur(statutUtilisateurRepository.findByCode("INACTIF").get());
    else
      utilisateur.setStatutUtilisateur(statutUtilisateurRepository.findByCode("ACTIF").get());
    utilisateurRepository.save(utilisateur);
    return Response.ok().setPayload(utilisateur).setMessage("Status utilisateur mise a jour.");
  }
}
