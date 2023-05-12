package br.univesp.analisedados.repositorios;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.univesp.analisedados.dto.responses.ListaPibDto;
import br.univesp.analisedados.entidades.PaisAnoId;
import br.univesp.analisedados.entidades.Pib;

public interface PibRepository extends JpaRepository<Pib, PaisAnoId> {

	boolean existsById(PaisAnoId id);
	public final String listaPrincipal = """ 
			SELECT new br.univesp.analisedados.dto.responses.ListaPibDto 
			(p.id.year,p.id.idCountry,p.totalGdp,p.totalGdpMillion,p.gdpVariation,p.totalGdp / t.populationEst) 
			FROM Pib p
			LEFT JOIN TamanhoPopulacao t ON p.id = t.id
			WHERE 1 = 1
			""";
	@Query(listaPrincipal)
	Page<ListaPibDto> paginar(Pageable paginacao);
	@Query(listaPrincipal + """
			AND	(p.id.idCountry in :idPaises)
			""")
	Page<ListaPibDto> paginar(List<Integer> idPaises, Pageable paginacao);
}
