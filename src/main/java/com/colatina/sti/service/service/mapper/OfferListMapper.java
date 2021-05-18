package com.colatina.sti.service.service.mapper;


import com.colatina.sti.service.domain.Offer;
import com.colatina.sti.service.service.dto.offer.OfferListDTO;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring", uses = {ItemListMapper.class, SituationOfferMapper.class, UserListMapper.class})
public interface OfferListMapper extends EntityMapper<Offer, OfferListDTO> {

}
