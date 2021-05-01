package com.colatina.sti.service.service.mapper;


import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EntityMapper<T, D> {

    T toEntity(D dto);

    D toDTO(T entity);

    Iterable<D> iterableToDTO(Iterable<T> entities);

    List<D> listDTO(List<T> entities);

    List<T> listEntitys(List<D> dtos);

}
