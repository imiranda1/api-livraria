package com.web.icaro.apilivraria.repository;

import com.web.icaro.apilivraria.model.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LivroRepository extends JpaRepository<Livro,Long> {


    @Query(value="SELECT l FROM Livro l WHERE UPPER(l.nome) LIKE CONCAT('%',UPPER(:nome),'%')")
    List<Livro> buscarPorNome(@Param("nome") String nome);
    @Query(value="SELECT l FROM Livro l WHERE l.editora.id = :idCategoria")
    List<Livro> buscarIdCategoria(@Param("idCategoria") Long idCategoria);
    @Query(value="SELECT l FROM Livro l WHERE l.categoria.id = :idEditora")
    List<Livro> buscarIdEditora(@Param("idEditora") Long idEditora);
}