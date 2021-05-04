package com.colatina.sti.service.service.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO implements Serializable {

  private Long id;

  private LocalDate birthDate;

  private String name;

  private String email;

  private String cpf;

}
