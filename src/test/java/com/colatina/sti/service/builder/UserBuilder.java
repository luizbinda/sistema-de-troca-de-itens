package com.colatina.sti.service.builder;

import com.colatina.sti.service.domain.User;
import com.colatina.sti.service.service.UserService;
import com.colatina.sti.service.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Component
@Transactional
public class UserBuilder extends ConstrutorEntidade<User>{

  @Autowired
  private UserMapper userMapper;

  @Autowired
  private UserService userService;

  @Override
  public User construirEntidade() {
    User user = new User();
    user.setBirthDate(LocalDate.now());
    user.setCpf("12113131212");
    user.setEmail("email");
    user.setName("teste");
    return user;
  }

  @Override
  public User persistir(User entidade) {
    return userMapper.toEntity(userService.store(userMapper.toDTO(entidade)));
  }
}
