package com.web.icaro.apilivraria.repository;

import com.web.icaro.apilivraria.model.entity.Categoria;
import com.web.icaro.apilivraria.model.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria,Long> {
}