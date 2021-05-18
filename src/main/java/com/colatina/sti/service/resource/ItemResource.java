package com.colatina.sti.service.resource;

import com.colatina.sti.service.service.ItemService;
import com.colatina.sti.service.service.dto.item.ItemDTO;
import com.colatina.sti.service.service.dto.item.ItemListDTO;
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
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/itens")
@RequiredArgsConstructor
public class ItemResource {
  private final ItemService itemService;

  @GetMapping
  public ResponseEntity<List<ItemListDTO>> index() {
    return  new ResponseEntity<>(itemService.index(), HttpStatus.OK);
  }

  @GetMapping("user/{userId}")
  public ResponseEntity<List<ItemListDTO>> findAllByUser(@PathVariable Long userId) {
    return  new ResponseEntity<>(itemService.findAllByUser(userId), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ItemListDTO> show(@PathVariable Long id) {
    return  new ResponseEntity<>(itemService.show(id), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<ItemDTO> store(@Valid @RequestBody ItemDTO itemDto) {
    return  new ResponseEntity<>(itemService.save(itemDto), HttpStatus.CREATED);
  }

  @PutMapping
  public ResponseEntity<ItemDTO> update(@Valid @RequestBody ItemDTO itemDto) {
    return  new ResponseEntity<>(itemService.save(itemDto), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    itemService.delete(id);
    return new ResponseEntity<>(null, HttpStatus.OK);
  }

}
