package com.colatina.sti.service.service.dto.item;

import com.colatina.sti.service.service.dto.SelectDTO;
import com.colatina.sti.service.service.dto.imagem.ImageListDTO;
import com.colatina.sti.service.service.dto.user.UserListDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ItemListDTO implements Serializable {
  private Long id;

  private String name;

  private String description;

  private Boolean available;

  private String situation;

  private SelectDTO category;

  private UserListDTO user;

  private List<ImageListDTO> images;
}
