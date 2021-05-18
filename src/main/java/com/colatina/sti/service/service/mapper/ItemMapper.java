package com.colatina.sti.service.service.mapper;

import com.colatina.sti.service.domain.Item;
import com.colatina.sti.service.service.dto.item.ItemDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ItemMapper extends EntityMapper<Item, ItemDTO> {
  @Override
  @Mapping(source = "categoryId", target = "category.id")
  @Mapping(source = "userId", target = "user.id")
  Item toEntity(ItemDTO dto);

  @Override
  @InheritInverseConfiguration
  ItemDTO toDTO(Item entity);
}
