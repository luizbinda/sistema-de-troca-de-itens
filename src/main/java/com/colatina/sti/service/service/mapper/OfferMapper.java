package com.colatina.sti.service.service.mapper;

import com.colatina.sti.service.domain.Item;
import com.colatina.sti.service.domain.Offer;
import com.colatina.sti.service.service.dto.offer.OfferDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {ItemListMapper.class, SituationOfferMapper.class, UserListMapper.class})
public interface OfferMapper extends EntityMapper<Offer, OfferDTO> {

  @AfterMapping
  default void setItensOffers(OfferDTO dto, @MappingTarget Offer entity) {
    entity.setItemsOffered(
            dto.getItemsOffered().stream().map( id -> {
              Item item = new Item();
              item.setId(id);
              return item;
            }).collect(Collectors.toList())
    );
  }

  @Override
  @Mapping(source = "itemId", target = "item.id")
  @Mapping(source = "userId", target = "user.id")
  @Mapping(source = "situationId", target = "situation.id")
  @Mapping(target = "itemsOffered", ignore = true)
  Offer toEntity(OfferDTO dto);

  @Override
  @InheritInverseConfiguration
  @Mapping(target = "itemsOffered", ignore = true)
  OfferDTO toDTO(Offer entity);


}
