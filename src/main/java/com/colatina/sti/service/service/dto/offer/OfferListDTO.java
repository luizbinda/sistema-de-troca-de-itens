package com.colatina.sti.service.service.dto.offer;

import com.colatina.sti.service.service.dto.SelectDTO;
import com.colatina.sti.service.service.dto.item.ItemListDTO;
import com.colatina.sti.service.service.dto.user.UserListDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OfferListDTO implements Serializable {

  private Long id;

  private ItemListDTO item;

  private UserListDTO user;

  private SelectDTO situation;

  private List<ItemListDTO> itemsOffered;

}
