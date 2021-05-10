package com.colatina.sti.service.builder;

import com.colatina.sti.service.domain.Item;
import com.colatina.sti.service.domain.Offer;
import com.colatina.sti.service.service.OfferService;
import com.colatina.sti.service.service.mapper.OfferMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;


@Component
public class OfferBuilder extends ConstrutorEntidade<Offer>{

  @Autowired
  private OfferMapper offerMapper;

  @Autowired
  private UserBuilder userBuilder;

  @Autowired
  private ItemBuilder itemBuilder;

  @Autowired
  private OfferService offerService;

  @Override
  public Offer construirEntidade() {
    Offer offer = new Offer();
    offer.setItem(itemBuilder.construir());
    List<Item> itemsOffered = new ArrayList<>();
    userBuilder.customizar(user -> {
      user.setCpf("41887469044");
      user.setEmail("emial@emial.com.br");
    });
    itemsOffered.add(itemBuilder.construir());
    offer.setItemsOffered(itemsOffered);
    userBuilder.customizar(user -> {
      user.setCpf("10084507071");
      user.setEmail("emial@emial.com");
    });
    offer.setUser(userBuilder.construir());
    return offer;
  }

  @Override
  public Offer persistir(Offer entidade) {
    return offerMapper.toEntity(offerService.save(offerMapper.toDTO(entidade)));
  }
}
