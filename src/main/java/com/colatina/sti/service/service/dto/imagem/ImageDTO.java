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

  private Item item;

  private byte[] photo;

  private String description;

  public ImageDTO(byte[] photo, Long itemId, String description) {
    this.photo = photo;
    this.description = description;
    this.item = new Item();
    this.item.setId(itemId);
  }

  public ImageDTO(byte[] photo, Long itemId, String description, Long id) {
    this.id = id;
    this.photo = photo;
    this.description = description;
    this.item = new Item();
    this.item.setId(itemId);
  }
}
