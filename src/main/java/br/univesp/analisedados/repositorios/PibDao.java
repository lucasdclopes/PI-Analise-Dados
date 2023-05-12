package br.univesp.analisedados.repositorios;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.univesp.analisedados.dto.responses.ListaPibDto;

public class PibDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	Page<ListaPibDto> paginar(List<Integer> idPaises, Pageable paginacao) {
		return null;
	}

}
