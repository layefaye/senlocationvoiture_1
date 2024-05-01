package com.wagaane.senlocation.services.implementations;

import com.wagaane.senlocation.entities.File;
import com.wagaane.senlocation.entities.Utilisateur;
import com.wagaane.senlocation.entities.Voiture;
import com.wagaane.senlocation.repositories.FileRepository;
import com.wagaane.senlocation.repositories.UtilisateurRepository;
import com.wagaane.senlocation.repositories.VoitureRepository;
import com.wagaane.senlocation.services.interfaces.IFile;
import com.wagaane.senlocation.web.dtos.responses.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class FIleService implements IFile {
  @Value("${upload.images_voiture}")
  private String upaloadCarImages;
  private final VoitureRepository voitureRepository;
  private final FileRepository fileRepository;
  @Override
  public ResponseEntity<Response<Object>> uploadFile(List<MultipartFile> files, long id, String type) {
    for (MultipartFile file : files) {
      try {
        switch (type){
            case "images_car":
              Optional<Voiture> optionalVoiture = voitureRepository.findById(id);
              if (optionalVoiture.isEmpty()) {
                return ResponseEntity.ok(Response.exception().setMessage("Utilisateur inexistant !"));
              }

              Voiture  voiture =  optionalVoiture.get();
              String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
              Path path = Paths.get(upaloadCarImages + fileName);
              Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
              File fileToSave = new File();
              fileToSave.setOriginalName(file.getOriginalFilename());
              fileToSave.setGeneratedName(fileName);
              fileToSave.setFileSize(file.getSize());
              File savedFile = fileRepository.save(fileToSave);
              voiture.getImages().add(savedFile);
              voitureRepository.save(voiture);
            break;

            case "profiles":
              System.out.println("");
              break;

        }

      } catch (IOException e) {
        throw new RuntimeException("Une erreur s'est produite lors de l'enregistrement de l'image", e);
      }
    }
    return ResponseEntity.ok(Response.ok().setPayload("Fichier enregistre avec succes."));
  }
}
