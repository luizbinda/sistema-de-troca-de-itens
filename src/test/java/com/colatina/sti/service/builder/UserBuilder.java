package com.colatina.sti.service.builder;

import com.colatina.sti.service.domain.User;
import com.colatina.sti.service.service.UserService;
import com.colatina.sti.service.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Component
public class UserBuilder extends ConstrutorEntidade<User>{

  @Autowired
  private UserMapper userMapper;

  @Autowired
  private UserService userService;

  @Override
  public User construirEntidade() {
    User user = new User();
    user.setBirthDate(LocalDate.of(1980, 10,10));
    user.setCpf("66326270057");
    user.setEmail("email@gmail.com");
    user.setName("teste");
    return user;
  }

  @Override
  public User persistir(User entidade) {
    return userMapper.toEntity(userService.store(userMapper.toDTO(entidade)));
  }
}
