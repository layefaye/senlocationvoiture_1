package com.wagaane.senlocation.web.controllers;

import com.wagaane.senlocation.services.interfaces.IFile;
import com.wagaane.senlocation.web.dtos.responses.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequestMapping("api/v1/senlocationvoiture/files")
@RequiredArgsConstructor
public class FileController {
  private final IFile iFile;

  @PostMapping(value = "/upload", consumes = {"multipart/form-data"})
  public ResponseEntity<Response<Object>> uploadImage(@RequestParam("files") List<MultipartFile> files,  long id, String type) {
     return ResponseEntity.ok(Response.ok().setPayload(iFile.uploadFile(files,id, type)));
  }
}
