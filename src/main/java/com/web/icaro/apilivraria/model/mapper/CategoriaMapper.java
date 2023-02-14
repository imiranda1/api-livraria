package com.web.icaro.apilivraria.model.mapper;

import com.web.icaro.apilivraria.model.dto.CategoriaDTO;
import com.web.icaro.apilivraria.model.entity.Categoria;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {
	List<CategoriaDTO> parseListDTO(List<Categoria> categorias);
	List<Categoria> parseListEntity(List<CategoriaDTO> categorias);
	CategoriaDTO parseDTO(Categoria categoria);
	Categoria parseEntity(CategoriaDTO categoria);

}
