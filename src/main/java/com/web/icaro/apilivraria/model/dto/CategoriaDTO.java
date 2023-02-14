package com.web.icaro.apilivraria.model.dto;

import com.web.icaro.apilivraria.model.entity.Livro;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoriaDTO {

    private Long id;

    @Length(max = 13, message = "máximo de 100 caracteres")
    private String nome;

}
