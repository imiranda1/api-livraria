package com.web.icaro.apilivraria.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "livro_favorito")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioLivroFavorito {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @JoinColumn(name = "livro_id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Livro livro;




}
