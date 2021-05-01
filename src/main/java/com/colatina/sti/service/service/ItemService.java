package com.colatina.sti.service.service;

import com.colatina.sti.service.domain.Category;
import com.colatina.sti.service.domain.Item;
import com.colatina.sti.service.domain.User;
import com.colatina.sti.service.repository.CategoryRepository;
import com.colatina.sti.service.repository.ItemRepository;
import com.colatina.sti.service.repository.UserRepository;
import com.colatina.sti.service.service.dto.item.ItemDTO;
import com.colatina.sti.service.service.exception.RegraNegocioException;
import com.colatina.sti.service.service.mapper.ItemMapper;
import com.colatina.sti.service.service.mapper.UserListMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

  private final ItemRepository itemRepository;
  private final ItemMapper itemMapper;
  private final UserRepository userRepository;
  private final CategoryRepository categoryRepository;

  public List<ItemDTO> index() {
    List<Item> list = itemRepository.findAll();
    return itemMapper.listToDTO(list);
  }

  public ItemDTO show(Long id) {
    Item item = itemRepository.findById(id)
            .orElseThrow(() -> new RegraNegocioException("Nenhum item encontrado!"));
    return itemMapper.toDTO(item);
  }

  public ItemDTO store(ItemDTO itemDto) {
    Item item = itemMapper.toEntity(itemDto);
    item = itemRepository.save(item);
    return itemMapper.toDTO(item);
  }

  public ItemDTO update(ItemDTO itemDto) {
    Item item = itemMapper.toEntity(itemDto);
    item = itemRepository.save(item);
    return itemMapper.toDTO(item);
  }

  public void delete(Long id) {
    itemRepository.deleteById(id);
  }
}
