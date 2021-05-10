package com.colatina.sti.service.service.dto.item;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class ItemDTO implements Serializable {

  private Long id;

  @NotNull(message = "Campo nome não pode ser nulo")
  @NotEmpty
  private String name;

  @NotNull(message = "Campo descrição não pode ser nulo")
  @NotEmpty
  private String description;

  @NotNull(message = "Campo disponibilidade não pode ser nulo")
  private Boolean available;

  @NotNull(message = "Campo categoria não pode ser nulo")
  private Long categoryId;

  @NotNull(message = "Campo Usuário não pode ser nulo")
  private Long userId;
}
