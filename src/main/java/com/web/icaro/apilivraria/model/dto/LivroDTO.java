package com.web.icaro.apilivraria.model.dto;

import com.web.icaro.apilivraria.model.entity.Categoria;
import com.web.icaro.apilivraria.model.entity.Editora;
import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;


@Data
@NoArgsConstructor
public class LivroDTO {

    private Long id;

    @NotBlank(message = "nome é requerido")
    private String nome;

    @NotNull(message = "categoria é requerido")
    private CategoriaDTO categoria;

    @NotNull(message = "editora é requerido")
    private EditoraDTO editora;

    @Length(max = 13, message = "máximo de 13 caracteres")
    private String isbn;

}
