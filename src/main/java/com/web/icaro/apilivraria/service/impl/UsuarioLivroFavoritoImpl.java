package com.web.icaro.apilivraria.service.impl;


import com.web.icaro.apilivraria.model.dto.LivroDTO;
import com.web.icaro.apilivraria.model.entity.Livro;
import com.web.icaro.apilivraria.model.entity.Usuario;
import com.web.icaro.apilivraria.model.entity.UsuarioLivroFavorito;
import com.web.icaro.apilivraria.model.mapper.LivroMapper;
import com.web.icaro.apilivraria.model.mapper.UsuarioLivroFavoritoMapper;
import com.web.icaro.apilivraria.repository.LivroRepository;
import com.web.icaro.apilivraria.repository.UsuarioLivroFavoritoRepository;
import com.web.icaro.apilivraria.repository.UsuarioRepository;
import com.web.icaro.apilivraria.service.UsuarioLivroFavoritoService;
import com.web.icaro.apilivraria.service.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class UsuarioLivroFavoritoImpl implements UsuarioLivroFavoritoService {


    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioLivroFavoritoRepository repository;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private LivroMapper livroMapper;

    @Override
    public List<LivroDTO> buscarFavoritos() {
        Usuario usuario = getUsuario();

        List<UsuarioLivroFavorito> favoritos = repository.buscarLivroPorUsuario(usuario.getId());

        //List<UsuarioLivroFavorito> favoritos2 = repository.findUsuarioLivroFavoritoByUsuario(usuario);
        List<Livro> listLivros= new ArrayList<>();
        favoritos.forEach(l->listLivros.add(l.getLivro()));

        return  livroMapper.parseListDTO(listLivros);
    }

    @Override
    public LivroDTO inserirFavorito(LivroDTO livroDTO) {

        if(!livroRepository.existsById(livroDTO.getId())){
            throw new EntityNotFoundException("Livro não cadastrado");
        }

        Livro livro = livroMapper.parseEntity(livroDTO);
        UsuarioLivroFavorito usuarioLivroFavorito = new UsuarioLivroFavorito();

        Usuario usuario = getUsuario();
        usuarioLivroFavorito.setUsuario(usuario);
        usuarioLivroFavorito.setLivro(livro);
        repository.save(usuarioLivroFavorito);
        return livroMapper.parseDTO(livro);

    }

    private Usuario getUsuario() {
        String username = String.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        Optional<Usuario> usuario = usuarioRepository.findByUsername(username);


        if (!usuario.isPresent()) {
            throw new UsernameNotFoundException("Usuário não encontrado.");
        }

        return usuario.get();
    }
}
