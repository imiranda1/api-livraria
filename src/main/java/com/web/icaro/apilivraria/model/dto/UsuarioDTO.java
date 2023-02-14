package com.web.icaro.apilivraria.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO{
	
		private Long id;
		private String nome;
		private String email;
}
