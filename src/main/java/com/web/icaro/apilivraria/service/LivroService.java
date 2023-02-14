package com.web.icaro.apilivraria.service;

import com.web.icaro.apilivraria.model.dto.LivroDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface LivroService extends BaseService<LivroDTO>{

    List<LivroDTO> buscarPorCategoria(Long categoria);

    List<LivroDTO> buscarPorEditora(Long editora);

    List<LivroDTO> buscarPorNomeOuIsbn(LivroDTO livroDTO);
}
