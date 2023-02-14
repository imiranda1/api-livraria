package com.web.icaro.apilivraria.model.mapper;

import com.web.icaro.apilivraria.model.dto.LivroDTO;
import com.web.icaro.apilivraria.model.entity.Livro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioLivroFavoritoMapper {
	List<LivroDTO> parseListDTO(List<Livro> livros);
	List<Livro> parseListEntity(List<LivroDTO> livros);
	LivroDTO parseDTO(Livro produto);

	@Mapping(target = "categoria.livros", ignore = true)
	Livro parseEntity(LivroDTO entidadeDTO);
}
