package com.web.icaro.apilivraria.service;

import com.web.icaro.apilivraria.model.dto.TokenDTO;
import com.web.icaro.apilivraria.model.dto.UsuarioLoginDTO;


public interface UsuarioService extends BaseService<UsuarioLoginDTO>{
	TokenDTO logar(UsuarioLoginDTO usuarioLoginDTO) throws Exception;
	TokenDTO atualizarToken(String refreshToken);


}
