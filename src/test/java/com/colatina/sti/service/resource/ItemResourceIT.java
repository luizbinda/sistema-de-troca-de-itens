package com.colatina.sti.service.resource;

import com.colatina.sti.service.ServiceApplication;
import com.colatina.sti.service.builder.ItemBuilder;
import com.colatina.sti.service.domain.Item;
import com.colatina.sti.service.service.Utils.ConstantsUtils;
import com.colatina.sti.service.service.mapper.ItemMapper;
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
import java.util.Objects;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@Transactional
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(classes = ServiceApplication.class)
class ItemResourceIT extends IntTestComum {

  @Autowired
  private ItemBuilder itemBuilder;

  @Autowired
  private ItemMapper itemMapper;

  private final String URL = "/api/itens";


  @Test
  public void searchAllItens() throws Exception {
    itemBuilder.construir();
    MockMvc mockaaa = getMockMvc();
    mockaaa.perform(get(URL)
            .contentType(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$",hasSize(1)));
  }

  @Test
  public void searchOneItem() throws Exception {
    Item item = itemBuilder.construir();
    getMockMvc().perform(get(URL + "/" + item.getId().toString())
            .contentType(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());
  }

  @Test
  public void storeItem() throws Exception{
    Item item = itemBuilder.construirEntidade();
    getMockMvc().perform(post(URL)
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemMapper.toDTO(item))))
            .andExpect(status().isCreated());
  }

  @Test
  public void tryStoreItemWithNullName() throws Exception{
    Item item = itemBuilder.construirEntidade();
    item.setName(null);
    getMockMvc().perform(post(URL)
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemMapper.toDTO(item))))
            .andExpect(status().isBadRequest())
            .andExpect(mvcResult -> Assertions.assertTrue(
                    Objects.requireNonNull(mvcResult.getResolvedException()).getMessage()
                            .contains(ConstantsUtils.ITEM_NAME_NOT_NULL)
            ));
  }

  @Test
  public void tryStoreItemWithEmpityName() throws Exception{
    Item item = itemBuilder.construirEntidade();
    item.setName("");
    getMockMvc().perform(post(URL)
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemMapper.toDTO(item))))
            .andExpect(status().isBadRequest())
            .andExpect(mvcResult -> Assertions.assertTrue(
                    Objects.requireNonNull(mvcResult.getResolvedException()).getMessage()
                            .contains(ConstantsUtils.ITEM_NAME_NOT_EMPITY)
            ));
  }

  @Test
  public void tryStoreItemWithNullAvailable() throws Exception{
    Item item = itemBuilder.construirEntidade();
    item.setAvailable(null);
    getMockMvc().perform(post(URL)
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemMapper.toDTO(item))))
            .andExpect(status().isBadRequest())
            .andExpect(mvcResult -> Assertions.assertTrue(
                    Objects.requireNonNull(mvcResult.getResolvedException()).getMessage()
                            .contains(ConstantsUtils.ITEM_AVALICAO_NOT_NULL)
            ));
  }

  @Test
  public void tryStoreItemWithNullDEscription() throws Exception{
    Item item = itemBuilder.construirEntidade();
    item.setDescription(null);
    getMockMvc().perform(post(URL)
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemMapper.toDTO(item))))
            .andExpect(status().isBadRequest())
            .andExpect(mvcResult -> Assertions.assertTrue(
                    Objects.requireNonNull(mvcResult.getResolvedException()).getMessage()
                            .contains(ConstantsUtils.ITEM_DESCRICAO_NOT_NULL)
            ));
  }

  @Test
  public void tryStoreItemWithEmpityDEscription() throws Exception{
    Item item = itemBuilder.construirEntidade();
    item.setDescription("");
    getMockMvc().perform(post(URL)
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemMapper.toDTO(item))))
            .andExpect(status().isBadRequest())
            .andExpect(mvcResult -> Assertions.assertTrue(
                    Objects.requireNonNull(mvcResult.getResolvedException()).getMessage()
                            .contains(ConstantsUtils.ITEM_DESCRICAO_NOT_EMPITY)
            ));
  }

  @Test
  public void tryStoreItemWithNullCategory() throws Exception{
    Item item = itemBuilder.construirEntidade();
    item.setCategory(null);
    getMockMvc().perform(post(URL)
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemMapper.toDTO(item))))
            .andExpect(status().isBadRequest())
            .andExpect(mvcResult -> Assertions.assertTrue(
                    Objects.requireNonNull(mvcResult.getResolvedException()).getMessage()
                            .contains(ConstantsUtils.ITEM_CATEGORIA_NOT_NULL)
            ));
  }

  @Test
  public void tryStoreItemWithNullUser() throws Exception{
    Item item = itemBuilder.construirEntidade();
    item.setUser(null);
    getMockMvc().perform(post(URL)
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemMapper.toDTO(item))))
            .andExpect(status().isBadRequest())
            .andExpect(mvcResult -> Assertions.assertTrue(
                    Objects.requireNonNull(mvcResult.getResolvedException()).getMessage()
                            .contains(ConstantsUtils.ITEM_USER_NOT_NULL)
            ));
  }

  @Test
  public void updateItem() throws Exception{
    Item item = itemBuilder.construir();
    item.setDescription("imagem alterada");
    getMockMvc().perform(put(URL)
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(itemMapper.toDTO(item))))
            .andExpect(status().isOk());
  }

}
