package com.colatina.sti.service.resource;

import com.colatina.sti.service.service.ImageService;
import com.colatina.sti.service.service.dto.imagem.ImageDTO;
import com.colatina.sti.service.service.dto.imagem.ImageListDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor
public class ImagemResource {
  private final ImageService imagemService;

  @GetMapping("/{id}")
  public ResponseEntity<ImageListDTO> show(@PathVariable Long id) {
    return  new ResponseEntity<>(imagemService.show(id), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<ImageListDTO> store(@RequestParam MultipartFile photo, Long itemId, String description) throws IOException {
    ImageDTO imageDTO = new ImageDTO(photo.getBytes(), itemId, description);
    return  new ResponseEntity<>(imagemService.store(imageDTO), HttpStatus.CREATED);
  }

  @PutMapping
  public ResponseEntity<ImageListDTO> update(@RequestParam MultipartFile photo, Long itemId, String description, Long id ) throws IOException {
    ImageDTO imageDTO = new ImageDTO(photo.getBytes(), itemId, description, id);
    return  new ResponseEntity<>(imagemService.update(imageDTO), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    imagemService.delete(id);
    return new ResponseEntity<>(null, HttpStatus.OK);
  }

}
