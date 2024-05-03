package com.wagaane.senlocation.services.interfaces;

import com.wagaane.senlocation.web.dtos.responses.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IFile {
  public Response<Object> uploadFile(List<MultipartFile> files, long id, String type);
}
