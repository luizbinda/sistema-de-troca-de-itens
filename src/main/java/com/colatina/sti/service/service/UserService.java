package com.colatina.sti.service.service;

import com.colatina.sti.service.domain.User;
import com.colatina.sti.service.repository.UserRepository;
import com.colatina.sti.service.service.dto.user.UserDTO;
import com.colatina.sti.service.service.dto.user.UserListDTO;
import com.colatina.sti.service.service.exception.RegraNegocioException;
import com.colatina.sti.service.service.mapper.UserListMapper;
import com.colatina.sti.service.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Random;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserListMapper userListMapper;
    private final UserMapper userMapper;
    private final Random rand = new Random();

    public List<UserListDTO> index() {
        List<User> list = userRepository.findAll();
        return userListMapper.listToDTO(list);
    }

    public UserDTO show(Long id) {
        User User = userRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Nenhum Usuário encontrado!"));
        return userMapper.toDTO(User);
    }

    public UserDTO store(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        user.setToken(Long.toHexString(rand.nextLong()));
        user = userRepository.save(user);
        return userMapper.toDTO(user);
    }

    public UserDTO update(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        user.setToken(userRepository.findById(userDTO.getId()).orElseThrow(()-> new RegraNegocioException("Nenhum Usuário encontrado!")).getToken());
        user = userRepository.save(user);
        return userMapper.toDTO(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
