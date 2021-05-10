package com.colatina.sti.service.builder;

import com.colatina.sti.service.domain.Item;
import com.colatina.sti.service.domain.User;
import com.colatina.sti.service.service.ItemService;
import com.colatina.sti.service.service.dto.item.ItemDTO;
import com.colatina.sti.service.service.mapper.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemBuilder extends ConstrutorEntidade<Item>{

  @Autowired
  private ItemMapper itemMapper;

  @Autowired
  private ItemService itemService;

  @Autowired
  private UserBuilder userBuilder;

  @Override
  public Item construirEntidade() {
    ItemDTO item = new ItemDTO();
    item.setName("item de teste");
    item.setDescription("description item de teste");
    item.setAvailable(true);
    User user = userBuilder.construir();
    item.setUserId(user.getId());
    item.setCategoryId(1L);
    return itemMapper.toEntity(item);
  }

  @Override
  public Item persistir(Item entidade) {
    return itemMapper.toEntity(itemService.store(itemMapper.toDTO(entidade)));
  }
}
