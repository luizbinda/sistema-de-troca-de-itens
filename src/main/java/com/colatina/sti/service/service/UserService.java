package com.colatina.sti.service.service;

import com.colatina.sti.service.domain.User;
import com.colatina.sti.service.repository.UserRepository;
import com.colatina.sti.service.service.dto.email.EmailDTO;
import com.colatina.sti.service.service.dto.user.UserDTO;
import com.colatina.sti.service.service.dto.user.UserListDTO;
import com.colatina.sti.service.service.exception.RegraNegocioException;
import com.colatina.sti.service.service.mapper.UserListMapper;
import com.colatina.sti.service.service.mapper.UserMapper;
import liquibase.pro.packaged.E;
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
    private final EmailService emailService;

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

        emailService.sendEmail(getEmail(user));

        return userMapper.toDTO(user);
    }

    private EmailDTO getEmail(User user){

        EmailDTO email = new EmailDTO();

        email.setAssunto("Cadastro STI");
        email.setCorpo("Cadastro realizado com sucesso no STI");
        email.setDestinatario("jhordan.eumesmo@gmail.com");

        return email;
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
