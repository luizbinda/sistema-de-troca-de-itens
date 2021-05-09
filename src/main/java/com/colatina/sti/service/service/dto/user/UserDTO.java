package com.colatina.sti.service.service.dto.user;

import com.colatina.sti.service.service.Utils.ConstantsUtils;
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

  @NotNull(message = ConstantsUtils.USER_BIRTH_DATE_NOT_NULL)
  @Past(message = ConstantsUtils.USER_BIRTH_DATE_PAST)
  private LocalDate birthDate;

  @NotBlank(message = ConstantsUtils.USER_NAME_NOT_EMPTY)
  @NotNull(message = ConstantsUtils.USER_NAME_NOT_NULL)
  private String name;

  @Email(message = ConstantsUtils.USER_EMAIL_FORMART)
  @NotBlank(message =ConstantsUtils.USER_EMAIL_NOT_EMPTY)
  @NotNull(message = ConstantsUtils.USER_EMAIL_NOT_NULL)
  private String email;

  @NotBlank(message = ConstantsUtils.USER_CPF_NOT_EMPTY)
  @NotNull(message = ConstantsUtils.USER_CPF_NOT_NULL)
  @CPF(message = ConstantsUtils.USER_CPF_FORMART)
  private String cpf;

}
