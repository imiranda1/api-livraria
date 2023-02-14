package com.web.icaro.apilivraria.service.impl;

import com.web.icaro.apilivraria.model.dto.TokenDTO;
import com.web.icaro.apilivraria.model.dto.UsuarioDTO;
import com.web.icaro.apilivraria.model.dto.UsuarioLoginDTO;
import com.web.icaro.apilivraria.model.entity.Livro;
import com.web.icaro.apilivraria.model.entity.Usuario;
import com.web.icaro.apilivraria.model.mapper.UsuarioMapper;
import com.web.icaro.apilivraria.repository.UsuarioRepository;
import com.web.icaro.apilivraria.service.UsuarioService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private UsuarioMapper mapper;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private JWTService jwtService;

	@Autowired
	private AuthService authService;

	@Autowired
	private AuthenticationManager authenticationManager;

	public List<UsuarioLoginDTO> buscarTodos() {
		return mapper.parseListDTO(repository.findAll());
	}

	public UsuarioLoginDTO buscarUm(Long id) {
		Optional<Usuario> usuarioOp = repository.findById(id);
		if(usuarioOp.isPresent()) {
			Usuario usuario = usuarioOp.get();
			return mapper.parseDTO(usuario);
		}

		throw new EntityNotFoundException();
	}

	public UsuarioLoginDTO criar(UsuarioLoginDTO clienteDTO) {
		Usuario usuario = mapper.parseEntity(clienteDTO);
		usuario.setPassword(encoder.encode(usuario.getPassword()));
		usuario.setId(null);
		repository.save(usuario);
		return mapper.parseDTO(usuario);
	}

	public UsuarioLoginDTO editar(Long id, UsuarioLoginDTO usuarioDTO) {

		Optional<Usuario> usuarioOp = repository.findById(id);

		if(usuarioOp.isPresent()) {
			Usuario usuario = usuarioOp.get();
			usuario.setNome(usuarioDTO.getNome());
			usuario.setEmail(usuarioDTO.getEmail());
			usuario.setId(id);
			usuario = repository.save(usuario);
			return mapper.parseDTO(usuario);
		}

		throw new EntityNotFoundException();
	}

	public void excluir(Long id) {
		if(!repository.existsById(id)) {
			throw new EntityNotFoundException();
		}

		repository.deleteById(id);
	}


	public TokenDTO atualizarToken(String refreshToken) {

		if(jwtService.validRefreshToken(refreshToken)) {
			String username = jwtService.getUsernameByRefreshToken(refreshToken);

			return buildTokenDTO(username,null);
		}

		throw new ExpiredJwtException(null, null,"Refresh token foi expirado.");
	}



	public TokenDTO logar(UsuarioLoginDTO usuarioLoginDTO) throws AuthenticationException,UsernameNotFoundException {

		UsernamePasswordAuthenticationToken autentication =
				new UsernamePasswordAuthenticationToken(usuarioLoginDTO.getUsername(),usuarioLoginDTO.getPassword());

		authenticationManager.authenticate(autentication);

		Usuario usuario = (Usuario) authService.loadUserByUsername(usuarioLoginDTO.getUsername());

		return buildTokenDTO(usuario.getUsername(),usuario);
	}

	private TokenDTO buildTokenDTO(String username,Usuario usuario) {

		UsuarioDTO usuarioDTO = null;
		if(Objects.nonNull(usuario)) {
			usuarioDTO = new UsuarioDTO();
			usuarioDTO.setId(usuario.getId());
			usuarioDTO.setNome(usuario.getNome());
			usuarioDTO.setEmail(usuario.getEmail());

		}

		String token = jwtService.generateToken(username);
		String refreshToken = jwtService.generateRefreshToken(username);
		return TokenDTO.builder()
				.token(token)
				.refreshToken(refreshToken)
				.type("Bearer")
				.user(usuarioDTO)
				.build();
	}

}
