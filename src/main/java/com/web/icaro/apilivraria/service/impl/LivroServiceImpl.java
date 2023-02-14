package com.web.icaro.apilivraria.service.impl;

import com.web.icaro.apilivraria.model.dto.LivroDTO;
import com.web.icaro.apilivraria.model.entity.Livro;
import com.web.icaro.apilivraria.model.mapper.LivroMapper;
import com.web.icaro.apilivraria.repository.LivroRepository;
import com.web.icaro.apilivraria.repository.LivroFilterRepository;
import com.web.icaro.apilivraria.service.LivroService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroServiceImpl implements LivroService {

    @Autowired
    private LivroMapper mapper;

    @Autowired
    private LivroRepository repository;

    @Autowired
    private LivroFilterRepository filterRepository;

    @PersistenceContext
    private EntityManager em;


    public List<LivroDTO> buscarTodos() {
        return mapper.parseListDTO(repository.findAll());
    }

    public LivroDTO buscarUm(Long id) {
        Optional<Livro> livroOptional = repository.findById(id);
        if(livroOptional.isPresent()) {
            return mapper.parseDTO(livroOptional.get());
        }
        throw new EntityNotFoundException();
    }

    @Transactional
    public LivroDTO criar(LivroDTO dto) {
        Livro livro = mapper.parseEntity(dto);
        livro.setId(null);
        repository.save(livro);
        em.refresh(livro);
        return mapper.parseDTO(livro);
    }



    public LivroDTO editar(Long id, LivroDTO livroDto) {
        if(repository.existsById(id)) {
            Livro livro = mapper.parseEntity(livroDto);
            livro.setId(id);
            livro = repository.save(livro);
            return mapper.parseDTO(livro);
        }

        throw new EntityNotFoundException();
    }

    public void excluir(Long id) {
        if(!repository.existsById(id)) {
            throw new EntityNotFoundException();
        }

        repository.deleteById(id);
    }

    @Override
    public List<LivroDTO> buscarPorCategoria(Long categoria) {
        return mapper.parseListDTO(repository.buscarIdCategoria(categoria));
    }

    @Override
    public List<LivroDTO> buscarPorEditora(Long editora) {
        return mapper.parseListDTO(repository.buscarIdEditora(editora));
    }


    public List<LivroDTO> buscarPorNomeOuIsbn(LivroDTO livroDTO) {
        Livro livro = mapper.parseEntity(livroDTO);
        return mapper.parseListDTO(
                filterRepository.filtrar(livro));
    }
}
