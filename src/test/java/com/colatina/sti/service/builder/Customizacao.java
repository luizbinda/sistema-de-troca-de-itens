package com.colatina.sti.service.builder;

public interface Customizacao<E> {

    void executar(E entidade);
}
