package com.colatina.sti.service.builder;

import com.colatina.sti.service.domain.Image;
import com.colatina.sti.service.service.ImageService;
import com.colatina.sti.service.service.dto.imagem.ImageDTO;
import com.colatina.sti.service.service.dto.imagem.ImageListDTO;
import com.colatina.sti.service.service.mapper.ImageListMapper;
import com.colatina.sti.service.service.mapper.ImageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionMessage;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

@Component
public class ImageBuilder extends ConstrutorEntidade<Image>{

  @Autowired
  private ImageMapper imageMapper;

  @Autowired
  private ImageListMapper imageListMapper;

  @Autowired
  private ImageService imageService;

  @Autowired
  private ItemBuilder itemBuilder;

  private String photo = "sdefebednmsadnfn";

  @Override
  public Image construirEntidade() {
    Image image = new Image();
    image.setDescription("imagem teste");
    image.setItem(itemBuilder.construirEntidade());
    image.setPhoto(photo.getBytes(StandardCharsets.UTF_8));
    return image;
  }

  @Override
  public Image persistir(Image entidade) {
    ImageListDTO store = imageService.store(imageMapper.toDTO(entidade));
    return imageListMapper.toEntity(store);
  }
}
