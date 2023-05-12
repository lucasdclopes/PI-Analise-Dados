package br.univesp.analisedados.repositorios;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.univesp.analisedados.dto.responses.ListaTamanhoPopulacaoDto;
import br.univesp.analisedados.entidades.PaisAnoId;
import br.univesp.analisedados.entidades.Pib;

public interface TamanhoPopulacaoRepository extends JpaRepository<Pib, PaisAnoId> {

	boolean existsById(PaisAnoId id);
	
	@Query(""" 
			SELECT new br.univesp.analisedados.dto.responses.ListaTamanhoPopulacaoDto 
			(id.year,id.idCountry,populationEst) 
			FROM TamanhoPopulacao t
			""")
	Page<ListaTamanhoPopulacaoDto> paginar(Pageable paginacao);
}
