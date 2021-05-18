package com.colatina.sti.service.service.mapper;

import java.util.List;

public interface EntityMapper<Entity, DTO> {

    Entity toEntity(DTO dto);

    DTO toDTO(Entity entity);

    Iterable<DTO> iterableToDTO(Iterable<Entity> entities);

    List<DTO> listToDTO(List<Entity> entities);

    List<Entity> listToEntitys(List<DTO> dtos);

}
