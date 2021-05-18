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
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
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
  public void searchAllImages() throws Exception {
    Image image = imageBuilder.construir();
    MockMultipartFile photo
            = new MockMultipartFile(
            "photo",
            "photo.jpg",
            MediaType.TEXT_PLAIN_VALUE,
            image.getPhoto()
    );

    HashMap<String, String> contentTypeParams = new HashMap<String, String>();
    contentTypeParams.put("boundary", "265001916915724");
    MediaType mediaType = new MediaType("multipart", "form-data", contentTypeParams);

   getMockMvc().perform(post(URL)
            .param("imageDTO", "{\n" +
                    "\t\"description\" : \"description\",\n" +
                    "\t\"item\" : {\n" +
                    "\t\t\"id\": 1\n" +
                    "\t}\n" +
                    "}")
            .content(photo.getBytes()).contentType(mediaType))
            .andReturn().getResponse().getContentAsString();
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
    Image image = imageBuilder.construir();
    MockMultipartFile photo
            = new MockMultipartFile(
            "photo",
            "photo.jpg",
            MediaType.TEXT_PLAIN_VALUE,
            image.getPhoto()
    );

    HashMap<String, String> contentTypeParams = new HashMap<String, String>();
    contentTypeParams.put("boundary", "265001916915724");
    MediaType mediaType = new MediaType("multipart", "form-data", contentTypeParams);

    getMockMvc().perform(post(URL)
            .param("imageDTO", "{\n" +
                    "\t\"description\" : \"description\",\n" +
                    "\t\"item\" : {\n" +
                    "\t\t\"id\": 1\n" +
                    "\t}\n" +
                    "}")
            .content(photo.getBytes()).contentType(mediaType))
            .andReturn().getResponse().getContentAsString();
  }

  @Test
  public void updateImage() throws Exception{
    Image image = imageBuilder.construir();
    image.setDescription("imagem alterada");
    MockMultipartFile photo
            = new MockMultipartFile(
            "photo",
            "photo.jpg",
            MediaType.TEXT_PLAIN_VALUE,
            image.getPhoto()
    );

    HashMap<String, String> contentTypeParams = new HashMap<String, String>();
    contentTypeParams.put("boundary", "265001916915724");
    MediaType mediaType = new MediaType("multipart", "form-data", contentTypeParams);

    getMockMvc().perform(put(URL)
            .param("imageDTO", "{\n" +
                    "\t\"description\" : \"description\",\n" +
                    "\t\"item\" : {\n" +
                    "\t\t\"id\": 1\n" +
                    "\t}\n" +
                    "}")
            .content(photo.getBytes()).contentType(mediaType))
            .andReturn().getResponse().getContentAsString();
  }

}
