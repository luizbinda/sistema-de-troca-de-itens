package com.colatina.sti.service.service.mapper;

import com.colatina.sti.service.domain.Category;
import com.colatina.sti.service.service.dto.SelectDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends EntityMapper<Category, SelectDTO> {
}
