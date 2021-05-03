package com.colatina.sti.service.service;

import com.colatina.sti.service.domain.Image;
import com.colatina.sti.service.repository.ImageRepository;
import com.colatina.sti.service.service.dto.imagem.ImageDTO;
import com.colatina.sti.service.service.dto.imagem.ImageListDTO;
import com.colatina.sti.service.service.exception.RegraNegocioException;
import com.colatina.sti.service.service.mapper.ImageListMapper;
import com.colatina.sti.service.service.mapper.ImageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
public class ImageService {

  private final ImageRepository imageRepository;
  private final ImageMapper imageMapper;
  private final ImageListMapper imageListMapper;

  public ImageListDTO show(Long id) {
    Image image = imageRepository.findById(id)
            .orElseThrow(() -> new RegraNegocioException("Nenhuma imagem encontrada!"));
    return imageListMapper.toDTO(image);
  }

  public ImageListDTO store(ImageDTO imageDTO) {
    Image image = imageMapper.toEntity(imageDTO);
    image = imageRepository.save(image);
    return imageListMapper.toDTO(image);
  }

  public ImageListDTO update(ImageDTO imageDTO) {
    Image image = imageMapper.toEntity(imageDTO);
    image = imageRepository.save(image);
    return imageListMapper.toDTO(image);
  }

  public void delete(Long id) {
    imageRepository.deleteById(id);
  }
}
