package com.colatina.sti.service.service.dto.imagem;

import com.colatina.sti.service.domain.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImageDTO implements Serializable {

  private Long id;

  private Long itemId;

  @NotNull(message = "Campo foto não pode ser nulo")
  private byte[] photo;

  @NotNull(message = "Campo descrição não pode ser nulo")
  @NotEmpty(message = "Campo descrição não pode estar vazio")
  private String description;

  public ImageDTO(byte[] photo, Long itemId, String description) {
    this.photo = photo;
    this.description = description;
    this.itemId = itemId;
  }
}
