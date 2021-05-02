package com.colatina.sti.service.service.dto.imagem;

import com.colatina.sti.service.domain.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
public class ImageDTO implements Serializable {

  private Long id;

  private Item Item;

  private byte[] photo;

  private String description;
}
