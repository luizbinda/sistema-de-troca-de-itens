package com.colatina.sti.service.resource;

import com.colatina.sti.service.ServiceApplication;
import com.colatina.sti.service.builder.ImageBuilder;
import com.colatina.sti.service.domain.Image;
import com.colatina.sti.service.service.mapper.ImageMapper;
import com.colatina.sti.service.util.IntTestComum;
import com.colatina.sti.service.util.TestUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
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
class ImageResourceIT extends IntTestComum {

  @Autowired
  private ImageBuilder imageBuilder;

  @Autowired
  private ImageMapper imageMapper;

  private final String URL = "/api/images";


  @Test
  public void searchAllUsers() throws Exception {
    imageBuilder.construir();
    MockMvc mockaaa = getMockMvc();
    mockaaa.perform(get(URL)
            .contentType(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$",hasSize(1)));
  }

  @Test
  public void searchOneImage() throws Exception {
    Image image = imageBuilder.construir();
    getMockMvc().perform(get(URL + "/" + image.getId().toString())
            .contentType(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());
  }

  @Test
  public void storeImage() throws Exception{
    Image image = imageBuilder.construirEntidade();
    getMockMvc().perform(post(URL)
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(imageMapper.toDTO(image))))
            .andExpect(status().isCreated());
  }

  @Test
  public void updateImage() throws Exception{
    Image image = imageBuilder.construir();
    image.setDescription("imagem alterada");
    getMockMvc().perform(put(URL)
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(imageMapper.toDTO(image))))
            .andExpect(status().isOk());
  }

}