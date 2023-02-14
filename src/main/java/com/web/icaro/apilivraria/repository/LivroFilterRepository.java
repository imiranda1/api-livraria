package com.web.icaro.apilivraria.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.web.icaro.apilivraria.model.entity.Livro;
import com.web.icaro.apilivraria.model.entity.QLivro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class LivroFilterRepository extends QuerydslRepositorySupport {

	public LivroFilterRepository() {
		super(Livro.class);
	}
	
	@PersistenceContext
	private EntityManager em;

	public List<Livro> filtrar(Livro filter){

		QLivro produto = QLivro.livro;
		
		List<Predicate> predicates = new ArrayList<>();
		
		if(filter.getNome() != null) {
			predicates.add(produto.nome.likeIgnoreCase("%"+filter.getNome()+"%"));
		}
		
		if(filter.getIsbn() != null) {
			predicates.add(produto.isbn.like(filter.getIsbn()));
		}
		
		return new JPAQueryFactory(em).selectFrom(produto).where(
					predicates.toArray(new Predicate[0])
				).fetch();
	}

}
