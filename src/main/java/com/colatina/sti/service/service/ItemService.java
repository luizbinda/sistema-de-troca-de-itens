package com.colatina.sti.service.service;

import com.colatina.sti.service.domain.Item;
import com.colatina.sti.service.repository.ItemRepository;
import com.colatina.sti.service.service.dto.ItemDTO;
import com.colatina.sti.service.service.mapper.ItemMapper;
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

  public List<ItemDTO> index() {
    List<Item> list = itemRepository.findAll();
    return itemMapper.listToDTO(list);
  }

  public ItemDTO show(String id) {
    return null;
  }

  public ItemDTO store(ItemDTO item) {
    return null;
  }

  public ItemDTO update(ItemDTO item) {
    return null;
  }

  public void delete(Long id) {
      }
}
