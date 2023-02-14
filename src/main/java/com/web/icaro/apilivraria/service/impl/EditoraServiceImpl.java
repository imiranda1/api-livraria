package com.web.icaro.apilivraria.service.impl;

import com.web.icaro.apilivraria.model.dto.EditoraDTO;
import com.web.icaro.apilivraria.model.entity.Editora;
import com.web.icaro.apilivraria.model.entity.Livro;
import com.web.icaro.apilivraria.model.mapper.EditoraMapper;
import com.web.icaro.apilivraria.service.EditoraService;
import com.web.icaro.apilivraria.repository.EditoraRepository;
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
public class EditoraServiceImpl implements EditoraService {

    @Autowired
    private EditoraMapper mapper;

    @Autowired
    private EditoraRepository repository;

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<EditoraDTO> buscarTodos() {
        return mapper.parseListDTO(repository.findAll());
    }

    public EditoraDTO buscarUm(Long id) {
        Optional<Editora> editoraOptional = repository.findById(id);
        if(editoraOptional.isPresent()) {
            return mapper.parseDTO(editoraOptional.get());
        }
        throw new EntityNotFoundException();
    }

    @Transactional
    public EditoraDTO criar(EditoraDTO dto) {
        Editora editora = mapper.parseEntity(dto);
        editora.setId(null);
        repository.save(editora);
        em.refresh(editora);
        return mapper.parseDTO(editora);
    }


    @Override
    public EditoraDTO editar(Long id, EditoraDTO dto) {
        if(repository.existsById(id)) {
            Editora editora = mapper.parseEntity(dto);
            editora.setId(id);
            editora = repository.save(editora);
            return mapper.parseDTO(editora);
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
