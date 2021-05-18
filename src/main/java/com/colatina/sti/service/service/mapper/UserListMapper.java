package com.colatina.sti.service.service.mapper;

import com.colatina.sti.service.domain.User;
import com.colatina.sti.service.service.dto.user.UserListDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserListMapper extends EntityMapper<User, UserListDTO> {
}
