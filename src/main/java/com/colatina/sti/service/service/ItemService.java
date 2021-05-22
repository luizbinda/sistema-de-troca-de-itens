package com.colatina.sti.service.service;

import com.colatina.sti.service.domain.Item;
import com.colatina.sti.service.domain.ItemSave;
import com.colatina.sti.service.repository.ItemRepository;
import com.colatina.sti.service.repository.ItemSaveRepository;
import com.colatina.sti.service.service.dto.item.ItemDTO;
import com.colatina.sti.service.service.dto.item.ItemListDTO;
import com.colatina.sti.service.service.exception.RegraNegocioException;
import com.colatina.sti.service.service.mapper.ItemListMapper;
import com.colatina.sti.service.service.mapper.ItemMapper;
import com.colatina.sti.service.service.mapper.ItemSaveMapper;
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
  private final ItemListMapper itemListMapper;
  private final ItemSaveMapper itemSaveMapper;
  private final ItemSaveRepository itemSaveRepository;


  public List<ItemListDTO> index() {
    List<Item> list = itemRepository.findAll();
    return itemListMapper.listToDTO(list);
  }

  public List<ItemListDTO> findAllByUser(Long userId) {
    List<Item> list = itemRepository.findAllByUserId(userId);
    return itemListMapper.listToDTO(list);
  }

  public ItemListDTO show(Long id) {
    Item item = itemRepository.findById(id)
            .orElseThrow(() -> new RegraNegocioException("Nenhum item encontrado!"));
    return itemListMapper.toDTO(item);
  }

  public ItemDTO save(ItemDTO itemDto) {
    ItemSave item = itemSaveMapper.toEntity(itemDto);
    item = itemSaveRepository.save(item);
    return itemSaveMapper.toDTO(item);
  }

  public List<ItemDTO> saveAll(List<ItemDTO> itemsDto) {
    List<ItemSave> item = itemSaveMapper.listToEntitys(itemsDto);
    item = itemSaveRepository.saveAll(item);
    return itemSaveMapper.listToDTO(item);
  }

  public void delete(Long id) {
    try {
      itemRepository.deleteById(id);
    } catch (Exception e) {
      throw  new RegraNegocioException("Nenhum item encontrado!");
    }
  }
}
