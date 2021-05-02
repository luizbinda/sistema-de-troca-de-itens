package com.colatina.sti.service.resource;

import com.colatina.sti.service.service.ItemService;
import com.colatina.sti.service.service.dto.item.ItemDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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
  public ResponseEntity<ItemDTO> show(@PathVariable Long id) {
    return  new ResponseEntity<>(itemService.show(id), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<ItemDTO> store(@RequestBody ItemDTO itemDto) {
    return  new ResponseEntity<>(itemService.store(itemDto), HttpStatus.CREATED);
  }

  @PutMapping
  public ResponseEntity<ItemDTO> update(@RequestBody ItemDTO itemDto) {
    return  new ResponseEntity<>(itemService.update(itemDto), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    itemService.delete(id);
    return new ResponseEntity<>(null, HttpStatus.OK);
  }

}