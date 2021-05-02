package com.colatina.sti.service.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class SelectDTO implements Serializable {
  private Long id;

  private String description;

}
