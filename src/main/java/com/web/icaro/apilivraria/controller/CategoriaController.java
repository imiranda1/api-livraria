package com.web.icaro.apilivraria.controller;

import com.web.icaro.apilivraria.model.dto.CategoriaDTO;
import com.web.icaro.apilivraria.service.CategoriaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/categorias")
@Slf4j
public class CategoriaController extends BaseController<CategoriaDTO, CategoriaService>{

    public CategoriaController(CategoriaService service) {
        super(service);
    }
}
