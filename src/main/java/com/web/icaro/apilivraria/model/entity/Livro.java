package com.web.icaro.apilivraria.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "livro")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Livro {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="nome")
    private String nome;

    @JoinColumn(name = "editora_id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Editora editora;

    @JoinColumn(name = "categoria_id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Categoria categoria;

    @Column(name="isbn")
    private String isbn;

}
