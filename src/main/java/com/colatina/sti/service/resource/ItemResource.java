package com.colatina.sti.service.resource;

import com.colatina.sti.service.service.ItemService;
import com.colatina.sti.service.service.dto.ItemDto;
import com.colatina.sti.service.service.dto.ItemListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/itens")
@RequiredArgsConstructor
public class ItemResource {
  private final ItemService itemService;

  @GetMapping
  public ResponseEntity<List<ItemListDto>> index() {
    return null;
  }

  @GetMapping("/{id}")
  public ResponseEntity<ItemDto> show(@PathVariable String id) {
    return null;
  }

  @PostMapping
  public ResponseEntity<ItemDto> store(ItemDto item) {
    return null;
  }

  @PutMapping
  public ResponseEntity<ItemDto> update(ItemDto item) {
    return null;
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ItemDto> delete(@PathVariable Long id) {
    return null;
  }

}
