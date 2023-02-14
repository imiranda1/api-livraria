package com.web.icaro.apilivraria.controller;

import com.web.icaro.apilivraria.model.dto.LivroDTO;
import com.web.icaro.apilivraria.service.LivroService;
import jakarta.validation.constraints.NotEmpty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/livros")
@Slf4j
public class LivroController extends BaseController<LivroDTO, LivroService>{

    public LivroController(LivroService service) {
        super(service);
    }

    @GetMapping("/buscar/categoria")
    public List<LivroDTO> buscarPorCategoria(@RequestParam int categoria) {
        return null;
    }

    @GetMapping("/buscar/editora")
    public List<LivroDTO> buscarPorEditora(@RequestParam Long editora) {
        return service.buscarPorEditora(editora);
    }

    @PostMapping("/buscar/nome_ou_isbn")
    public List<LivroDTO> buscarPorNomeOuIsbn(@RequestBody @NotEmpty LivroDTO livroDTO ) {
        return service.buscarPorNomeOuIsbn(livroDTO);
    }
}
