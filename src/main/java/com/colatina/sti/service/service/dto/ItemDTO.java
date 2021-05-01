package com.colatina.sti.service.service.dto;

import com.colatina.sti.service.domain.Category;
import com.colatina.sti.service.domain.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDTO {
  private Long id;

  private String name;

  private String description;

  private Boolean available;

  private String situation;

  private Category category;

  private User user;
}
