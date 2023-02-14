package com.web.icaro.apilivraria.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioLoginDTO extends UsuarioDTO{
	
		private String username;
		private String password;
}
