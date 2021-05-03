package com.colatina.sti.service.service;

import com.colatina.sti.service.domain.User;
import com.colatina.sti.service.repository.UserRepository;
import com.colatina.sti.service.service.dto.user.UserListDTO;
import com.colatina.sti.service.service.exception.RegraNegocioException;
import com.colatina.sti.service.service.mapper.UserListMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserListMapper userListMapper;

    public List<UserListDTO> index() {
        List<User> list = userRepository.findAll();
        return userListMapper.listToDTO(list);
    }

    public UserListDTO show(Long id) {
        User User = userRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Nenhuma Oferta encontrado!"));
        return userListMapper.toDTO(User);
    }

    public UserListDTO store(UserListDTO UserListDTO) {
        User User = userListMapper.toEntity(UserListDTO);
        User = userRepository.save(User);
        return userListMapper.toDTO(User);
    }

    public UserListDTO update(UserListDTO UserListDTO) {
        User User = userListMapper.toEntity(UserListDTO);
        User = userRepository.save(User);
        return userListMapper.toDTO(User);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
