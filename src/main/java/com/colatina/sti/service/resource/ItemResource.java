package com.colatina.sti.service.resource;

import com.colatina.sti.service.service.ItemService;
import com.colatina.sti.service.service.dto.ItemDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
  public ResponseEntity<List<ItemDTO>> index() {
    return  new ResponseEntity<>(itemService.index(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ItemDTO> show(@PathVariable String id) {
    return null;
  }

  @PostMapping
  public ResponseEntity<ItemDTO> store(ItemDTO item) {
    return null;
  }

  @PutMapping
  public ResponseEntity<ItemDTO> update(ItemDTO item) {
    return null;
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<ItemDTO> delete(@PathVariable Long id) {
    return null;
  }

}
