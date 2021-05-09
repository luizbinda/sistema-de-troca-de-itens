package com.colatina.sti.service.builder;

import com.colatina.sti.service.domain.Offer;
import com.colatina.sti.service.domain.User;
import com.colatina.sti.service.service.OfferService;
import com.colatina.sti.service.service.mapper.OfferMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class OfferBuilder extends ConstrutorEntidade<Offer>{

  @Autowired
  private OfferMapper offerMapper;

  @Autowired
  private UserBuilder userBuilder;

  @Autowired
  private OfferService offerService;

  @Override
  public Offer construirEntidade() {
    Offer offer = new Offer();

    offer.setUser(userBuilder.construir());
//    offer.setItem();
//    offer.getItemsOffered();
    return offer;
  }

  @Override
  public Offer persistir(Offer entidade) {
    return offerMapper.toEntity(offerService.store(offerMapper.toDTO(entidade)));
  }
}
