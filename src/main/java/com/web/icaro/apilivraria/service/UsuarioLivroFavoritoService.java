package com.web.icaro.apilivraria.service;


import com.web.icaro.apilivraria.model.dto.LivroDTO;

import java.util.List;

public interface UsuarioLivroFavoritoService{
    List<LivroDTO> buscarFavoritos();
    LivroDTO inserirFavorito(LivroDTO livro);
}
