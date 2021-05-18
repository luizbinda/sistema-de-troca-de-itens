package com.colatina.sti.service.service.mapper;

import com.colatina.sti.service.domain.Image;
import com.colatina.sti.service.service.dto.imagem.ImageListDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImageListMapper extends EntityMapper<Image, ImageListDTO> {
}
