package com.colatina.sti.service.resource;

import com.colatina.sti.service.ServiceApplication;
import com.colatina.sti.service.builder.UserBuilder;
import com.colatina.sti.service.domain.User;
import com.colatina.sti.service.service.Utils.ConstantsUtils;
import com.colatina.sti.service.service.mapper.UserMapper;
import com.colatina.sti.service.util.IntTestComum;
import com.colatina.sti.service.util.TestUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Objects;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@Transactional
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(classes = ServiceApplication.class)
class UserResourceIT extends IntTestComum {

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
  public void searchForANonExistingUser() throws Exception {
    getMockMvc().perform(get(URL + "/1")
            .contentType(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isBadRequest())
            .andExpect(mvcResult -> Assertions.assertTrue(
                    Objects.requireNonNull(mvcResult.getResolvedException()).getMessage()
                            .contains(ConstantsUtils.USER_NOT_FOUND)
            ));
  }

  @Test
  public void tryStoreOneUserWithBirthDateNull() throws Exception{
    User user = builder.construirEntidade();
    user.setBirthDate(null);
    getMockMvc().perform(post(URL)
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userMapper.toDTO(user))))
            .andExpect(status().isBadRequest())
            .andExpect(mvcResult -> Assertions.assertTrue(
                    Objects.requireNonNull(mvcResult.getResolvedException()).getMessage()
                            .contains(ConstantsUtils.USER_BIRTH_DATE_NOT_NULL)
            ));
  }

  @Test
  public void tryStoreOneUserWithBirthDateNotInPast() throws Exception{
    User user = builder.construirEntidade();
    user.setBirthDate(LocalDate.now());
    getMockMvc().perform(post(URL)
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userMapper.toDTO(user))))
            .andExpect(status().isBadRequest())
            .andExpect(mvcResult -> Assertions.assertTrue(
                    Objects.requireNonNull(mvcResult.getResolvedException()).getMessage()
                            .contains(ConstantsUtils.USER_BIRTH_DATE_PAST)
            ));
  }

  @Test
  public void tryStoreOneUserWithNameNull() throws Exception{
    User user = builder.construirEntidade();
    user.setName(null);
    getMockMvc().perform(post(URL)
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userMapper.toDTO(user))))
            .andExpect(status().isBadRequest())
            .andExpect(mvcResult -> Assertions.assertTrue(
                    Objects.requireNonNull(mvcResult.getResolvedException()).getMessage()
                            .contains(ConstantsUtils.USER_NAME_NOT_NULL)
            ));
  }

  @Test
  public void tryStoreOneUserWithNameEmpty() throws Exception{
    User user = builder.construirEntidade();
    user.setName("");
    getMockMvc().perform(post(URL)
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userMapper.toDTO(user))))
            .andExpect(status().isBadRequest())
            .andExpect(mvcResult -> Assertions.assertTrue(
                    Objects.requireNonNull(mvcResult.getResolvedException()).getMessage()
                            .contains(ConstantsUtils.USER_NAME_NOT_EMPTY)
            ));
  }

  @Test
  public void tryStoreOneUserWithEmailInvalid() throws Exception{
    User user = builder.construirEntidade();
    user.setEmail("email invalido");
    getMockMvc().perform(post(URL)
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userMapper.toDTO(user))))
            .andExpect(status().isBadRequest())
            .andExpect(mvcResult -> Assertions.assertTrue(
                    Objects.requireNonNull(mvcResult.getResolvedException()).getMessage()
                            .contains(ConstantsUtils.USER_EMAIL_FORMART)
            ));
  }

  @Test
  public void tryStoreOneUserWithEmailNull() throws Exception{
    User user = builder.construirEntidade();
    user.setEmail(null);
    getMockMvc().perform(post(URL)
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userMapper.toDTO(user))))
            .andExpect(status().isBadRequest())
            .andExpect(mvcResult -> Assertions.assertTrue(
                    Objects.requireNonNull(mvcResult.getResolvedException()).getMessage()
                            .contains(ConstantsUtils.USER_EMAIL_NOT_NULL)
            ));
  }

  @Test
  public void tryStoreOneUserWithCpfNull() throws Exception{
    User user = builder.construirEntidade();
    user.setCpf(null);
    getMockMvc().perform(post(URL)
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userMapper.toDTO(user))))
            .andExpect(status().isBadRequest())
            .andExpect(mvcResult -> Assertions.assertTrue(
                    Objects.requireNonNull(mvcResult.getResolvedException()).getMessage()
                            .contains(ConstantsUtils.USER_CPF_NOT_NULL)
            ));
  }

  @Test
  public void tryStoreOneUserWithCpfEmpty() throws Exception{
    User user = builder.construirEntidade();
    user.setCpf("");
    getMockMvc().perform(post(URL)
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userMapper.toDTO(user))))
            .andExpect(status().isBadRequest())
            .andExpect(mvcResult -> Assertions.assertTrue(
                    Objects.requireNonNull(mvcResult.getResolvedException()).getMessage()
                            .contains(ConstantsUtils.USER_CPF_NOT_EMPTY)
            ));
  }

  @Test
  public void tryStoreOneUserWithCpfInvalid() throws Exception{
    User user = builder.construirEntidade();
    user.setCpf("123123");
    getMockMvc().perform(post(URL)
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userMapper.toDTO(user))))
            .andExpect(status().isBadRequest())
            .andExpect(mvcResult -> Assertions.assertTrue(
                    Objects.requireNonNull(mvcResult.getResolvedException()).getMessage()
                            .contains(ConstantsUtils.USER_CPF_FORMART)
            ));
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
    builder.construir();
    User user = builder.construirEntidade();
    user.setEmail("outroemail@teste.com");
    getMockMvc().perform(post(URL)
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userMapper.toDTO(user))))
            .andExpect(status().isBadRequest())
            .andExpect(mvcResult -> Assertions.assertTrue(
                    Objects.requireNonNull(mvcResult.getResolvedException()).getMessage()
                            .contains(ConstantsUtils.USER_CPF_DUPLICATE)
            ));
  }

  @Test
  public void storeOneUserWithEmailRepeat() throws Exception{
    builder.construir();
    User user = builder.construirEntidade();
    user.setCpf("41887469044");
    getMockMvc().perform(post(URL)
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userMapper.toDTO(user))))
            .andExpect(status().isBadRequest())
            .andExpect(mvcResult -> Assertions.assertTrue(
                    Objects.requireNonNull(mvcResult.getResolvedException()).getMessage()
                            .contains(ConstantsUtils.USER_EMAIL_DUPLICATE)
            ));
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

  @Test
  public void updateANonExistingUser() throws Exception {
    User user = builder.construirEntidade();
    user.setId(1L);
    getMockMvc().perform(put(URL)
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(userMapper.toDTO(user))))
            .andExpect(status().isBadRequest())
            .andExpect(mvcResult -> Assertions.assertTrue(
                    Objects.requireNonNull(mvcResult.getResolvedException()).getMessage()
                            .contains(ConstantsUtils.USER_NOT_FOUND)
            ));
  }

}
