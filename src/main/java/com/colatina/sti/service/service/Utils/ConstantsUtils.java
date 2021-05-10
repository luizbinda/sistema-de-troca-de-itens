package com.colatina.sti.service.service.Utils;

public class ConstantsUtils {
  public static final Long SITUATION_PENDING = 1L;
  public static final Long SITUATION_ACCEPTED = 2L;
  public static final Long SITUATION_REFUSED = 3L;


  // User validation Messages
  public static final String USER_BIRTH_DATE_NOT_NULL = "Data de nascimento é obrigatoria!";
  public static final String USER_BIRTH_DATE_PAST = "Data de nascimento deve ser uma data passada!";

  public static final String USER_NAME_NOT_EMPTY = "Nome não pode ser vazio!";
  public static final String USER_NAME_NOT_NULL = "Nome é obrigatorio!";

  public static final String USER_EMAIL_NOT_EMPTY = "Email é obrigatorio!";
  public static final String USER_EMAIL_NOT_NULL = "Email não pode ser vazio!";
  public static final String USER_EMAIL_FORMART = "Email em formato inválido!";

  public static final String USER_CPF_NOT_EMPTY = "Cpf é obrigatorio!";
  public static final String USER_CPF_NOT_NULL = "Cpf não pode ser vazio!";
  public static final String USER_CPF_FORMART = "Cpf em formato inválido!";

  public static final String USER_CPF_DUPLICATE = "Cpf já cadastrado no sistema!";
  public static final String USER_EMAIL_DUPLICATE = "Email já cadastrado no sistema!";
  public static final String USER_NOT_FOUND = "Nenhum Usuário encontrado!";

  // Offer validation Messages

  public static final String OFFER_ITEM_NOT_NULL = "Item da Oferta é obrigatorio!";
  public static final String OFFER_USER_NOT_NULL = "Usuario da Oferta é obrigatorio!";

  public static final String OFFER_ITEMS_OFFERED_NOT_NULL = "Itens ofertados são obrigatorios!";
  public static final String OFFER_ITEMS_OFFERED_NOT_EMPTY = "É necessário ao menos 1 item ofertado para criar uma oferta!";
  public static final String OFFER_NOT_FOUND = "Nenhuma Oferta encontrada!";



}
