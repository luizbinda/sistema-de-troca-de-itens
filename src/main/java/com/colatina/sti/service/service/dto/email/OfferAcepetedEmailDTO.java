package com.colatina.sti.service.service.dto.email;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OfferAcepetedEmailDTO implements Serializable {
  private String assunto;
  private String destinatario;
  private String userName;
  private String itemName;
  private String template;
  private List<String> copias = new ArrayList<>();
}
