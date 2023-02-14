package com.web.icaro.apilivraria.controller;

import com.web.icaro.apilivraria.model.dto.LivroDTO;
import com.web.icaro.apilivraria.service.UsuarioLivroFavoritoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/favoritos")
@Slf4j
public class UsuarioLivroFavoritoController {

    @Autowired
    private UsuarioLivroFavoritoService service;

    @PostMapping()
    public LivroDTO inserirFavorito(@RequestBody LivroDTO livroDTO) {
        return service.inserirFavorito(livroDTO);
    }

    @GetMapping()
    public List<LivroDTO> buscarFavoritos() {
        return service.buscarFavoritos();
    }
}
