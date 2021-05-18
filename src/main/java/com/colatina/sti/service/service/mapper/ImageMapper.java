package com.colatina.sti.service.service.mapper;

import com.colatina.sti.service.domain.Image;
import com.colatina.sti.service.service.dto.imagem.ImageDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImageMapper extends EntityMapper<Image, ImageDTO> {
}
