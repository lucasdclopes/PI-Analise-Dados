package br.univesp.analisedados.repositorios;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.univesp.analisedados.dto.responses.ListaPibDto;
import br.univesp.analisedados.dto.responses.PibCo2DadosDto;
import br.univesp.analisedados.entidades.PaisAnoId;
import br.univesp.analisedados.entidades.Pib;

public interface PibRepository extends JpaRepository<Pib, PaisAnoId> {

	boolean existsById(PaisAnoId id);
	
	public final String whereAno = """
			 (:minAno is null or p.id.year >= :minAno) AND
			 (:maxAno is null or p.id.year <= :maxAno)
			""";
	public final String wherePaises = """
			 AND (p.id.idCountry in :idPaises)
			""";
	public final String listaPrincipal = """ 
			SELECT new br.univesp.analisedados.dto.responses.ListaPibDto 
			(p.id.year,p.id.idCountry,p.totalGdp,p.totalGdpMillion,p.gdpVariation,p.totalGdp / t.populationEst) 
			FROM Pib p
			LEFT JOIN TamanhoPopulacao t ON p.id = t.id
			WHERE 
			""" + whereAno ;
	@Query(listaPrincipal)
	Page<ListaPibDto> paginar(Integer minAno, Integer maxAno,Pageable paginacao);
	
	@Query(listaPrincipal + wherePaises)
	Page<ListaPibDto> paginar(List<Integer> idPaises, Integer minAno, Integer maxAno, Pageable paginacao);
	
	public final String calcMedias = """ 
			SELECT new br.univesp.analisedados.dto.responses.PibCo2DadosDto
			(p.id.year,
			avg(p.totalGdp),min(p.totalGdp),max(p.totalGdp),
			avg(p.totalGdp/t.populationEst),min(p.totalGdp/t.populationEst),max(p.totalGdp/t.populationEst),
			avg(c.AnnualCo),min(c.AnnualCo),max(c.AnnualCo)
			) 
			FROM Pib p
			LEFT JOIN TamanhoPopulacao t ON p.id = t.id
			LEFT JOIN Co2 c ON p.id = c.id
			WHERE 
			c.AnnualCo != 0 AND p.totalGdp != 0 AND 
			""" + whereAno;
	@Query(calcMedias + " group by p.id.year order by p.id.year")
	List<PibCo2DadosDto> mediaCo(Integer minAno, Integer maxAno);
	
	@Query(calcMedias + wherePaises + " group by p.id.year order by p.id.year")
	List<PibCo2DadosDto> mediaCo(List<Integer> idPaises, Integer minAno, Integer maxAno);
	
	
}
