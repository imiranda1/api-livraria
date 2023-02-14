package com.web.icaro.apilivraria.model.mapper;

import com.web.icaro.apilivraria.model.dto.EditoraDTO;
import com.web.icaro.apilivraria.model.entity.Editora;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EditoraMapper {
	List<EditoraDTO> parseListDTO(List<Editora> editoras);
	List<Editora> parseListEntity(List<EditoraDTO> editoras);
	EditoraDTO parseDTO(Editora editora);
	Editora parseEntity(EditoraDTO editora);
}
