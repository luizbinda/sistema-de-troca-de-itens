package com.colatina.sti.service.service.mapper;

import com.colatina.sti.service.domain.Offer;
import com.colatina.sti.service.service.dto.offer.OfferDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ItemListMapper.class, SituationOfferMapper.class, UserListMapper.class})
public interface OfferMapper extends EntityMapper<Offer, OfferDTO> {
}
