package com.web.icaro.apilivraria.repository;

import com.web.icaro.apilivraria.model.entity.Editora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EditoraRepository extends JpaRepository<Editora,Long> {
}