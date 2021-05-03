package com.colatina.sti.service.resource;

import com.colatina.sti.service.service.UserService;
import com.colatina.sti.service.service.dto.user.UserListDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserResource {
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserListDTO> show(@PathVariable Long id) {
        return  new ResponseEntity<>(userService.show(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserListDTO> store(@RequestParam UserListDTO userListDTO) throws IOException {
        return  new ResponseEntity<>(userService.store(userListDTO), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<UserListDTO> update(@RequestParam UserListDTO userListDTO) throws IOException {
        return  new ResponseEntity<>(userService.update(userListDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}
