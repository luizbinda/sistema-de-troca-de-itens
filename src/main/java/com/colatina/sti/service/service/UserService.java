package com.colatina.sti.service.service;

import com.colatina.sti.service.domain.User;
import com.colatina.sti.service.repository.UserRepository;
import com.colatina.sti.service.service.Utils.ConstantsUtils;
import com.colatina.sti.service.service.Utils.OrderQueueSender;
import com.colatina.sti.service.service.dto.email.EmailDTO;
import com.colatina.sti.service.service.dto.user.UserDTO;
import com.colatina.sti.service.service.dto.user.UserListDTO;
import com.colatina.sti.service.service.exception.RegraNegocioException;
import com.colatina.sti.service.service.mapper.UserListMapper;
import com.colatina.sti.service.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final OrderQueueSender orderQueueSender;

    public List<UserListDTO> index() {
        List<User> list = userRepository.findAll();
        return userListMapper.listToDTO(list);
    }

    public UserDTO show(Long id) {
        User User = userRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException(ConstantsUtils.USER_NOT_FOUND));
        return userMapper.toDTO(User);
    }

    public UserDTO store(UserDTO userDTO) {
        if (checkDuplicateCpf(userDTO.getCpf())) {
            throw new RegraNegocioException(ConstantsUtils.USER_CPF_DUPLICATE);
        }

        if (checkDuplicateEmail(userDTO.getEmail())) {
            throw new RegraNegocioException(ConstantsUtils.USER_EMAIL_DUPLICATE);
        }

        User user = userMapper.toEntity(userDTO);
        user.setToken(Long.toHexString(rand.nextLong()));
        user = userRepository.save(user);

        orderQueueSender.send(getEmail(user));

        return userMapper.toDTO(user);
    }

    private Boolean checkDuplicateCpf(String cpf) {
        User user = userRepository.findDistinctFirstByCpf(cpf);
        return !(null == user);
    }

    private Boolean checkDuplicateEmail(String email) {
        User user = userRepository.findDistinctFirstByEmail(email);
        return !(null == user);
    }

    private EmailDTO getEmail(User user){

        EmailDTO email = new EmailDTO();
        email.setAssunto("Cadastro STI");
        email.setUserName(user.getName());
        email.setTemplate(ConstantsUtils.EMAIL_WELCOME);
        email.setDestinatario(user.getEmail());
        return email;
    }

    public UserDTO update(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        user.setToken(userRepository.findById(userDTO.getId())
                .orElseThrow(()-> new RegraNegocioException(ConstantsUtils.USER_NOT_FOUND)).getToken());
        user = userRepository.save(user);
        return userMapper.toDTO(user);
    }

    public void delete(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            throw  new RegraNegocioException(ConstantsUtils.USER_NOT_FOUND);
        }
    }
}
