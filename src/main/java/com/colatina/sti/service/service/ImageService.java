package com.colatina.sti.service.service;

import com.colatina.sti.service.domain.Image;
import com.colatina.sti.service.repository.ImageRepository;
import com.colatina.sti.service.service.dto.imagem.ImageDTO;
import com.colatina.sti.service.service.exception.RegraNegocioException;
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

  public ImageDTO show(Long id) {
    Image item = imageRepository.findById(id)
            .orElseThrow(() -> new RegraNegocioException("Nenhuma imagem encontrada!"));
    return imageMapper.toDTO(item);
  }

  public ImageDTO store(ImageDTO imageDTO) {
    Image item = imageMapper.toEntity(imageDTO);
    item = imageRepository.save(item);
    return imageMapper.toDTO(item);
  }

  public ImageDTO update(ImageDTO imageDTO) {
    Image item = imageMapper.toEntity(imageDTO);
    item = imageRepository.save(item);
    return imageMapper.toDTO(item);
  }

  public void delete(Long id) {
    imageRepository.deleteById(id);
  }
}
