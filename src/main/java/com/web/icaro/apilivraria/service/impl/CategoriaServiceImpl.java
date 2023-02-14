package com.web.icaro.apilivraria.service.impl;

import com.web.icaro.apilivraria.model.dto.CategoriaDTO;
import com.web.icaro.apilivraria.model.entity.Categoria;
import com.web.icaro.apilivraria.model.mapper.CategoriaMapper;
import com.web.icaro.apilivraria.service.CategoriaService;
import com.web.icaro.apilivraria.repository.CategoriaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaMapper mapper;

    @Autowired
    private CategoriaRepository repository;

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<CategoriaDTO> buscarTodos() {
        return mapper.parseListDTO(repository.findAll());
    }

    public CategoriaDTO buscarUm(Long id) {
        Optional<Categoria> categoriaOptional = repository.findById(id);
        if(categoriaOptional.isPresent()) {
            return mapper.parseDTO(categoriaOptional.get());
        }
        throw new EntityNotFoundException();
    }

    @Transactional
    public CategoriaDTO criar(CategoriaDTO dto) {
        Categoria categoria = mapper.parseEntity(dto);
        categoria.setId(null);
        repository.save(categoria);
        em.refresh(categoria);
        return mapper.parseDTO(categoria);
    }

    @Override
    public CategoriaDTO editar(Long id, CategoriaDTO dto) {
        if(repository.existsById(id)) {
            Categoria categoria = mapper.parseEntity(dto);
            categoria.setId(id);
            categoria = repository.save(categoria);
            return mapper.parseDTO(categoria);
        }

        throw new EntityNotFoundException();
    }

    public void excluir(Long id) {
        if(!repository.existsById(id)) {
            throw new EntityNotFoundException();
        }

        repository.deleteById(id);
    }
}
