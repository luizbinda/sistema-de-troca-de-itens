package com.colatina.sti.service.service.dto.imagem;

import com.colatina.sti.service.domain.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
public class ImageDTO implements Serializable {

  private Long id;

  @NotNull(message = "Campo item não pode ser nulo")
  private Item item;

  @NotNull(message = "Campo foto não pode ser nulo")
  private byte[] photo;

  @NotNull(message = "Campo descrição não pode ser nulo")
  @NotEmpty
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
