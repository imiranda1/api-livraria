package com.web.icaro.apilivraria.controller;

import com.web.icaro.apilivraria.model.dto.TokenDTO;
import com.web.icaro.apilivraria.model.dto.UsuarioLoginDTO;
import com.web.icaro.apilivraria.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/usuarios")
@Slf4j
public class UsuarioController extends BaseController<UsuarioLoginDTO, UsuarioService>  {
	
	public UsuarioController(UsuarioService service) {
		super(service);
	}
	
	@PostMapping("/auth")
	public ResponseEntity<TokenDTO> logar(@RequestBody @Valid UsuarioLoginDTO entidade) {
		try {

            return ResponseEntity.ok(service.logar(entidade));

        }catch(Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
	}
	
	@GetMapping("/auth/{refreshToken}")
	public ResponseEntity<TokenDTO> atualizarToken(@PathVariable("refreshToken") String refreshToken) {
		try {

            return ResponseEntity.ok(service.atualizarToken(refreshToken));

        }catch(Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
	}


}
