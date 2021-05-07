package com.colatina.sti.service.resource;


import com.colatina.sti.service.ServiceApplication;
import com.colatina.sti.service.builder.UserBuilder;
import com.colatina.sti.service.domain.User;
import com.colatina.sti.service.service.mapper.UserMapper;
import com.colatina.sti.service.util.IntTestComum;
import com.colatina.sti.service.util.TestUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceApplication.class)
public class UserResourceIT extends IntTestComum {

  @Autowired
  private UserBuilder builder;

  @Autowired
  private UserMapper userMapper;

  private final String URL = "/api/user";

  @Test
  public void listagem() throws Exception {
    builder.construir();
    MockMvc mockaaa = getMockMvc();
    mockaaa.perform(get(URL)
            .contentType(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$",hasSize(7)));

  }

  @Test
  public void salvar() throws Exception{
    User usuario = builder.construirEntidade();
    getMockMvc().perform(post(URL)
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userMapper.toDTO(usuario))))
            .andExpect(status().isCreated());
  }

}
