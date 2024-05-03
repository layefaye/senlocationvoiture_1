package com.wagaane.senlocation.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wagaane.senlocation.entities.Utilisateur;
import com.wagaane.senlocation.repositories.StatutUtilisateurRepository;
import com.wagaane.senlocation.repositories.UtilisateurRepository;
import com.wagaane.senlocation.security.config.JwtService;
import com.wagaane.senlocation.entities.Compte;
import com.wagaane.senlocation.repositories.CompteRepository;
import com.wagaane.senlocation.security.token.Token;
import com.wagaane.senlocation.security.token.TokenRepository;
import com.wagaane.senlocation.security.token.TokenType;
import com.wagaane.senlocation.web.dtos.responses.Response;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final CompteRepository compteRepository;
  private final TokenRepository tokenRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  private  final UtilisateurRepository utilisateurRepository;
  private final StatutUtilisateurRepository statutUtilisateurRepository;

  /**
   * inscription
   * @param request
   * @return
   */
  public Response<Object> register(RegisterRequest request) {

    // save compte
    var compte = Compte.builder().email(request.getEmail())
        .password(request.getPassword())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .role(request.getRole())
        .build();
   compteRepository.save(compte);

    // inscription utilisateur
    var utilisateur = Utilisateur.builder()
            .compte(compte)
            .nom(request.getNom())
            .prenom(request.getPrenom())
            .telephone(request.getTelephone())
            .adresse(request.getAdresse())
            .email(request.getEmail())
            .statutUtilisateur(statutUtilisateurRepository.findByCode("ACTIF").get())
            .build();

    utilisateurRepository.save(utilisateur);

    return Response.ok().setMessage("Inscription reussie avec succes");
  }


  /**
   * connexion
   * @param request
   * @return
   */
  public Response<Object> authenticate(AuthenticationRequest request) {
    Compte compte = new Compte();
    Optional<Compte> optionalCompte = compteRepository.findByEmail(request.getEmail());
    if (optionalCompte.isPresent()) {
       compte = optionalCompte.get();

    }else{
      Response.exception().setPayload("Nom d'utilisateur n'existe pas");
    }
    authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    request.getEmail(),
                    request.getPassword()
            )
    );
    var jwtToken = jwtService.generateToken(compte);
    var refreshToken = jwtService.generateRefreshToken(compte);

    revokeAllUserTokens(compte);
    saveUserToken(compte, jwtToken);
    return Response.ok().setMessage("Connexion reussie avec succes").setPayload(AuthenticationResponse.builder()
            .accessToken(jwtToken)
            .refreshToken(refreshToken)
            .build());
  }

  /**
   * enregistrer token
   * @param user
   * @param jwtToken
   */
  private void saveUserToken(Compte user, String jwtToken) {
    var token = Token.builder()
        .compte(user)
        .token(jwtToken)
        .tokenType(TokenType.BEARER)
        .expired(false)
        .revoked(false)
        .build();
    tokenRepository.save(token);
  }

  /**
   * supprimer token
   * @param compte
   */
  private void revokeAllUserTokens(Compte compte) {
    var validUserTokens = tokenRepository.findAllValidTokenByUser(compte.getId());
    if (validUserTokens.isEmpty())
      return;
    validUserTokens.forEach(token -> {
      token.setExpired(true);
      token.setRevoked(true);
    });
    tokenRepository.saveAll(validUserTokens);
  }

  public void refreshToken(
          HttpServletRequest request,
          HttpServletResponse response
  ) throws IOException {
    final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
    final String refreshToken;
    final String userEmail;
    if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
      return;
    }
    refreshToken = authHeader.substring(7);
    userEmail = jwtService.extractUsername(refreshToken);
    if (userEmail != null) {
      var user = this.compteRepository.findByEmail(userEmail)
              .orElseThrow();
      if (jwtService.isTokenValid(refreshToken, user)) {
        var accessToken = jwtService.generateToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, accessToken);
        var authResponse = AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
        new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
      }
    }
  }
}
