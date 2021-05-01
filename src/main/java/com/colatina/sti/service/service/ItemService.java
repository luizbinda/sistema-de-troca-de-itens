package com.colatina.sti.service.service;

import com.colatina.sti.service.domain.Item;
import com.colatina.sti.service.repository.ItemRepository;
import com.colatina.sti.service.service.dto.ItemDto;
import com.colatina.sti.service.service.dto.ItemListDto;
import com.colatina.sti.service.service.mapper.ItemListMapper;
import com.colatina.sti.service.service.mapper.ItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

  private final ItemRepository itemRepository;
  private final ItemMapper itemMapper;
  private final ItemListMapper itemListMapper;

  public ResponseEntity<List<ItemListDto>> index() {
    List<Item> list = itemRepository.findAll();
    return itemListMapper.listDTO(list);
  }

  public ResponseEntity<ItemDto> show( String id) {
    return null;
  }


  public ResponseEntity<ItemDto> store(ItemDto item) {
    return null;
  }

  public ResponseEntity<ItemDto> update(ItemDto item) {
    return null;
  }

  public ResponseEntity<ItemDto> delete(Long id) {
    return null;
  }
}
