package com.colatina.sti.service.service;

import com.colatina.sti.service.domain.Category;
import com.colatina.sti.service.repository.CategoryRepository;
import com.colatina.sti.service.service.dto.SelectDTO;
import com.colatina.sti.service.service.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService {

  private final CategoryRepository categoryRepository;
  private final CategoryMapper categoryMapper;

  public List<SelectDTO> index() {
    List<Category> list = categoryRepository.findAll();
    return categoryMapper.listToDTO(list);
  }

}
