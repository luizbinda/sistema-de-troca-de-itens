package com.colatina.sti.service.service.dto.item;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class ItemDTO implements Serializable {
  private Long id;

  private String name;

  private String description;

  private Boolean available;

  private Long categoryId;

  private Long userId;
}
