package com.colatina.sti.service.service.dto.offer;

import com.colatina.sti.service.service.Utils.ConstantsUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OfferDTO implements Serializable {

  private Long id;

  @NotNull(message = ConstantsUtils.OFFER_ITEM_NOT_NULL)
  private Long itemId;

  @NotNull(message = ConstantsUtils.OFFER_USER_NOT_NULL)
  private Long userId;

  private Long situationId;

  @NotEmpty(message = ConstantsUtils.OFFER_ITEMS_OFFERED_NOT_EMPTY)
  @NotNull(message = ConstantsUtils.OFFER_ITEMS_OFFERED_NOT_NULL)
  private List<Long> itemsOffered;

}
