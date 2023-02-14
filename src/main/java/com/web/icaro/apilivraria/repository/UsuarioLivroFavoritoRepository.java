package com.web.icaro.apilivraria.repository;

import com.web.icaro.apilivraria.model.entity.Livro;
import com.web.icaro.apilivraria.model.entity.Usuario;
import com.web.icaro.apilivraria.model.entity.UsuarioLivroFavorito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioLivroFavoritoRepository extends JpaRepository<UsuarioLivroFavorito,Long>{


    @Query(value="SELECT l FROM UsuarioLivroFavorito l WHERE l.usuario.id = :id")
    List<UsuarioLivroFavorito> buscarLivroPorUsuario(@Param("id") Long id);

    List<UsuarioLivroFavorito> findUsuarioLivroFavoritoByUsuario(Usuario usuario);

}

