package com.web.icaro.apilivraria.service;

import com.web.icaro.apilivraria.model.entity.Livro;

import java.util.List;

public interface BaseService<T> {
    List<T> buscarTodos();
    T buscarUm(Long id);
    T criar(T dto);
    T editar(Long id, T dto);
    void excluir(Long id);
}
