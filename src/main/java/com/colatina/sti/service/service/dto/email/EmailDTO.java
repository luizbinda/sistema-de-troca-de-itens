package com.colatina.sti.service.service.dto.email;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class EmailDTO implements Serializable {
  private String assunto;
  private String destinatario;
  private String corpo;
  private List<String> copias = new ArrayList<>();
}