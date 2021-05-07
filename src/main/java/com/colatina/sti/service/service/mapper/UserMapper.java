package com.colatina.sti.service.service.mapper;

import com.colatina.sti.service.domain.User;
import com.colatina.sti.service.service.dto.user.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<User, UserDTO> {
}
