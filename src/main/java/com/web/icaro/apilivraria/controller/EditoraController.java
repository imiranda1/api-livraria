package com.web.icaro.apilivraria.controller;

import com.web.icaro.apilivraria.model.dto.EditoraDTO;
import com.web.icaro.apilivraria.service.EditoraService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/editoras")
@Slf4j
public class EditoraController extends BaseController<EditoraDTO, EditoraService>{

    public EditoraController(EditoraService service) {
        super(service);
    }
}
