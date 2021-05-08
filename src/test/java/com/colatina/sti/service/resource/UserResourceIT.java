package com.colatina.sti.service.resource;

import com.colatina.sti.service.ServiceApplication;
import com.colatina.sti.service.builder.UserBuilder;
import com.colatina.sti.service.domain.User;
import com.colatina.sti.service.service.mapper.UserMapper;
import com.colatina.sti.service.util.IntTestComum;
import com.colatina.sti.service.util.TestUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest(classes = ServiceApplication.class)
public class UserResourceIT extends IntTestComum {

  @Autowired
  private UserBuilder builder;

  @Autowired
  private UserMapper userMapper;

  private final String URL = "/api/users";


  @Test
  public void searchAllUsers() throws Exception {
    builder.construir();
    MockMvc mockaaa = getMockMvc();
    mockaaa.perform(get(URL)
            .contentType(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$",hasSize(1)));
  }

  @Test
  public void searchOneUser() throws Exception {
    User user = builder.construir();
    getMockMvc().perform(get(URL + "/" + user.getId().toString())
            .contentType(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());
  }

  @Test
  public void tryStoreOneUserWithEmailInvalid() throws Exception{
    builder.customizar( userCustom -> userCustom.setEmail("email invalido"));
    User user = builder.construir();
    getMockMvc().perform(post(URL)
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userMapper.toDTO(user))))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.message", is("Email em formato inválido!")));
  }

  @Test
  public void storeOneUser() throws Exception{
    User user = builder.construirEntidade();
    getMockMvc().perform(post(URL)
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userMapper.toDTO(user))))
            .andExpect(status().isCreated());
  }

  @Test
  public void storeOneUserWithCpfRepeat() throws Exception{
    User user = builder.construir();
    user.setEmail("outro email");
    getMockMvc().perform(post(URL)
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userMapper.toDTO(user))))
            .andExpect(status().isCreated())
            .andExpect(content().json("{'message':'ok'}"));
  }


  @Test
  public void updateOneUser() throws Exception{
    User user = builder.construir();
    user.setName("outro nome");
    getMockMvc().perform(put(URL)
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userMapper.toDTO(user))))
            .andExpect(status().isOk());
  }

}
