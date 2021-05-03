package com.colatina.sti.service.service.dto.imagem;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class ImageListDTO implements Serializable {

  private Long id;

  private byte[] photo;

  private String description;
}
