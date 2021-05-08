package com.colatina.sti.service.service.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO implements Serializable {

  private Long id;

  @NotNull(message = "Data de nascimento é obrigatoria!")
  @Past(message = "Data de nascimento deve ser uma data passada!")
  private LocalDate birthDate;

  @NotBlank(message = "Nome não pode ser vazio!")
  @NotNull(message = "Nome é obrigatorio!")
  private String name;

  @Email(message = "Email em formato inválido!")
  @NotBlank(message = "Email não pode ser vazia!")
  @NotNull(message = "Email é obrigatorio!")
  private String email;

  @NotBlank(message = "Cpf não pode ser vazia!")
  @NotNull(message = "Cpf é obrigatorio!")
  @CPF(message = "Cpf em formato inválido!")
  private String cpf;

}
