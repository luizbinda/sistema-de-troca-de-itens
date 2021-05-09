package com.colatina.sti.service.service.Utils;

public class ConstantsUtils {
  public static final Long SITUATION_PENDING = 1L;
  public static final Long SITUATION_ACCEPTED = 2L;
  public static final Long SITUATION_REFUSED = 3L;


  // User validation Messages
  public static final String USER_BIRTH_DATE_NOT_NULL = "Data de nascimento é obrigatoria!";
  public static final String USER_BIRTH_DATE_PAST = "Data de nascimento deve ser uma data passada!";

  public static final String USER_NAME_NOT_NULL = "Nome não pode ser vazio!";
  public static final String USER_NAME_NOT_EMPTY = "Nome é obrigatorio!";

  public static final String USER_EMAIL_NOT_NULL = "Email é obrigatorio!";
  public static final String USER_EMAIL_NOT_EMPTY = "Email não pode ser vazia!";
  public static final String USER_EMAIL_FORMART = "Email em formato inválido!";

  public static final String USER_CPF_NOT_NULL = "Cpf é obrigatorio!";
  public static final String USER_CPF_NOT_EMPTY = "Cpf não pode ser vazia!";
  public static final String USER_CPF_FORMART = "Cpf em formato inválido!";

  public static final String USER_CPF_DUPLICATE = "Cpf já cadastrado no sistema!";
  public static final String USER_EMAIL_DUPLICATE = "Email já cadastrado no sistema!";
  public static final String USER_NOT_FOUND = "Nenhum Usuário encontrado!";



}
