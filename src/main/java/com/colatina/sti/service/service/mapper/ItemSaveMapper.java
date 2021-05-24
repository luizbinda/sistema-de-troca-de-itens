package com.colatina.sti.service.service.mapper;

import com.colatina.sti.service.domain.Item;
import com.colatina.sti.service.domain.ItemSave;
import com.colatina.sti.service.service.dto.item.ItemDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ItemSaveMapper extends EntityMapper<ItemSave, ItemDTO> {
  @Override
  @Mapping(source = "categoryId", target = "category.id")
  @Mapping(source = "userId", target = "user.id")
  ItemSave toEntity(ItemDTO dto);

  @Override
  @InheritInverseConfiguration
  ItemDTO toDTO(ItemSave entity);
}
