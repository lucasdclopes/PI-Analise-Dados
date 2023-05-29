package br.univesp.analisedados.repositorios;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.univesp.analisedados.dto.responses.ListaCo2Dto;
import br.univesp.analisedados.entidades.Co2;
import br.univesp.analisedados.entidades.PaisAnoId;
import br.univesp.analisedados.helpers.QueriesConstantes;

public interface Co2Repository extends JpaRepository<Co2, PaisAnoId> {

	boolean existsById(PaisAnoId id);
	public final String listaPrincipal = """ 
			SELECT 
			new br.univesp.analisedados.dto.responses.ListaCo2Dto 
			(c.id.year,c.id.idCountry,c.AnnualCo,
			""" + QueriesConstantes.CALC_CO2_PER_CAPITA
			+"""
			) 
			FROM Co2 c
			LEFT JOIN TamanhoPopulacao t ON c.id = t.id
			WHERE 
			(:minAno is null or c.id.year >= :minAno) AND
			(:maxAno is null or c.id.year <= :maxAno)
			""";
	@Query(listaPrincipal)
	Page<ListaCo2Dto> paginar(Integer minAno, Integer maxAno, Pageable paginacao);
	@Query(listaPrincipal + """
			AND (c.id.idCountry in :idPaises)
			""")
	Page<ListaCo2Dto> paginar(
			List<Integer> idPaises, Integer minAno, Integer maxAno, Pageable paginacao
			);

	
	
}
