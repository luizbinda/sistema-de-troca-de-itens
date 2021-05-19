package com.colatina.sti.service.service.mapper;

import com.colatina.sti.service.domain.Image;
import com.colatina.sti.service.domain.Item;
import com.colatina.sti.service.service.dto.imagem.ImageDTO;
import com.colatina.sti.service.service.dto.item.ItemDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ImageMapper extends EntityMapper<Image, ImageDTO> {

  @Override
  @Mapping(source="itemId", target="item.id")
  Image toEntity(ImageDTO dto);
}
