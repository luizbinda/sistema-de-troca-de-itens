package com.colatina.sti.service.resource;

import com.colatina.sti.service.service.UserService;
import com.colatina.sti.service.service.dto.user.UserDTO;
import com.colatina.sti.service.service.dto.user.UserListDTO;
import com.colatina.sti.service.service.dto.user.UserLoginDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserResource {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserListDTO>> index() {
        return new ResponseEntity<>(userService.index(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> show(@PathVariable Long id) {
        return new ResponseEntity<>(userService.show(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDTO> store(@RequestBody @Valid UserDTO userDTO) {
        return new ResponseEntity<>(userService.store(userDTO), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody @Valid UserLoginDTO userLoginDTO) {
        UserDTO userDTO = userService.login(userLoginDTO);
        if(userDTO.getId() == null)
            return new ResponseEntity<>(userService.login(userLoginDTO), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(userService.login(userLoginDTO), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<UserDTO> update(@RequestBody @Valid UserDTO userDTO) {
        return new ResponseEntity<>(userService.update(userDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}
