package com.web.icaro.apilivraria.model.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EditoraDTO {

    @Column(name="id")
    private Long id;

    @NotBlank(message = "nome é requerido")
    @Length(max = 255, message = "máximo de 255 caracteres")
    private String nome;

    private String descricao;
}
