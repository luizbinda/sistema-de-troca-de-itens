package com.colatina.sti.service.service;

import com.colatina.sti.service.domain.User;
import com.colatina.sti.service.repository.UserRepository;
import com.colatina.sti.service.service.Utils.ConstantsUtils;
import com.colatina.sti.service.service.Utils.OrderQueueSender;
import com.colatina.sti.service.service.dto.email.WelcomeEmailDTO;
import com.colatina.sti.service.service.dto.user.UserDTO;
import com.colatina.sti.service.service.dto.user.UserListDTO;
import com.colatina.sti.service.service.dto.user.UserLoginDTO;
import com.colatina.sti.service.service.exception.RegraNegocioException;
import com.colatina.sti.service.service.mapper.UserListMapper;
import com.colatina.sti.service.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserListMapper userListMapper;
    private final UserMapper userMapper;
    private final EmailService emailService;
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

    public UserDTO login(UserLoginDTO userLoginDTO) {
        User user = userRepository.findDistinctFirstByEmail(userLoginDTO.getEmail());
        if (user != null && user.getToken() != null){
            if(new BCryptPasswordEncoder(). matches(userLoginDTO.getPassword(), user.getToken()))
                return userMapper.toDTO(user);
        }
        return userMapper.toDTO(new User());
    }

    public UserDTO store(UserDTO userDTO) {
        if (checkDuplicateCpf(userDTO.getCpf())) {
            throw new RegraNegocioException(ConstantsUtils.USER_CPF_DUPLICATE);
        }

        if (checkDuplicateEmail(userDTO.getEmail())) {
            throw new RegraNegocioException(ConstantsUtils.USER_EMAIL_DUPLICATE);
        }

        User user = userMapper.toEntity(userDTO);
        user.setToken(new BCryptPasswordEncoder().encode(userDTO.getEmail()));
        user = userRepository.save(user);

        orderQueueSender.send(getEmail(user));
        emailService.sendEmail(getEmail(user));

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

    private WelcomeEmailDTO getEmail(User user){

        WelcomeEmailDTO email = new WelcomeEmailDTO();
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

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findDistinctFirstByEmail(email);

        return new org.springframework.security.core.userdetails.
                User(
                        user.getEmail(),
                        user.getToken(),
                        AuthorityUtils.createAuthorityList("admin")
        );
    }
}
