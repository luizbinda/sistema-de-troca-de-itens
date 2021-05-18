package com.colatina.sti.service.resource;

import com.colatina.sti.service.ServiceApplication;
import com.colatina.sti.service.builder.OfferBuilder;
import com.colatina.sti.service.domain.Offer;
import com.colatina.sti.service.service.Utils.ConstantsUtils;
import com.colatina.sti.service.service.mapper.OfferMapper;
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
import org.springframework.transaction.annotation.Transactional;
import java.util.Objects;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@Transactional
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(classes = ServiceApplication.class)
class OfferResourceIT extends IntTestComum {

  @Autowired
  private OfferBuilder builder;

  @Autowired
  private OfferMapper offerMapper;

  private final String URL = "/api/offers";

  @Test
  public void searchOneOffer() throws Exception {
    Offer offer = builder.construir();
    getMockMvc().perform(get(URL + "/" + offer.getId().toString())
            .contentType(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());
  }

  @Test
  public void searchAllOfferOfOneItem() throws Exception {
    Offer offer = builder.construir();
    getMockMvc().perform(get(URL + "/item/" + offer.getItem().getId().toString())
            .contentType(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());
  }

  @Test
  public void searchForANonExistingOffer() throws Exception {
    getMockMvc().perform(get(URL + "/1")
            .contentType(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isBadRequest())
            .andExpect(mvcResult -> Assertions.assertTrue(
                    Objects.requireNonNull(mvcResult.getResolvedException()).getMessage()
                            .contains(ConstantsUtils.OFFER_NOT_FOUND)
            ));
  }

  @Test
  public void storeOneOffer() throws Exception{
    Offer offer = builder.construir();
    getMockMvc().perform(post(URL)
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(offerMapper.toDTO(offer))))
            .andExpect(status().isCreated());
  }

  @Test
  public void acceptOneOffer() throws Exception {
    Offer offer = builder.construir();
    getMockMvc().perform(patch(URL + "/accepted/" + offer.getId().toString())
            .contentType(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());
  }

  @Test
  public void refuseOneOffer() throws Exception {
    Offer offer = builder.construir();
    getMockMvc().perform(patch(URL + "/refused/" + offer.getId().toString())
            .contentType(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());
  }

}
