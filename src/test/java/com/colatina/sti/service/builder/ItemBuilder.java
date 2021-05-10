package com.colatina.sti.service.builder;

import com.colatina.sti.service.domain.Category;
import com.colatina.sti.service.domain.Item;
import com.colatina.sti.service.domain.User;
import com.colatina.sti.service.repository.CategoryRepository;
import com.colatina.sti.service.service.ItemService;
import com.colatina.sti.service.service.UserService;
import com.colatina.sti.service.service.mapper.ItemMapper;
import com.colatina.sti.service.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class ItemBuilder extends ConstrutorEntidade<Item>{

  @Autowired
  private ItemMapper itemMapper;

  @Autowired
  private ItemService itemService;

  @Autowired
  private UserBuilder userBuilder;

  @Autowired
  private CategoryRepository categoryRepository;


  @Override
  public Item construirEntidade() {
    Item item = new Item();
    item.setUser(userBuilder.construirEntidade());
    item.setName("Nome teste");
    item.setDescription("descrição");

    List<Category> categoryList = categoryRepository.findAll();
    if (categoryList != null && categoryList.size() > 0)
    item.setCategory(categoryList.get(0));

    return item;
  }

  @Override
  public Item persistir(Item entidade) {
    return itemMapper.toEntity(itemService.store(itemMapper.toDTO(entidade)));
  }
}
