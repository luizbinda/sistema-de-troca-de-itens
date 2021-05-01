package com.colatina.sti.service.service.mapper;

import com.colatina.sti.service.domain.Item;
import com.colatina.sti.service.service.dto.ItemDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemMapper extends EntityMapper<Item, ItemDTO> {
}
