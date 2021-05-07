package com.colatina.sti.service.service.dto.offer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OfferDTO implements Serializable {

  private Long id;

  private Long itemId;

  private Long userId;

  private Long situationId;

  private List<Long> itemsOffered;

}
