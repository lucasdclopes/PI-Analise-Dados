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
import br.univesp.analisedados.helpers.QueriesConstantes;

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
			SELECT 
			 new br.univesp.analisedados.dto.responses.ListaPibDto 
			(p.id.year,p.id.idCountry
			,p.totalGdp,p.totalGdpMillion,p.gdpVariation
			,""" + QueriesConstantes.CALC_PIB_PER_CAPITA 
			+"""
			)
			FROM Pib p
			LEFT JOIN TamanhoPopulacao t ON p.id = t.id
			WHERE 
			""" + whereAno ;
	@Query(listaPrincipal)
	Page<ListaPibDto> paginar(
			Integer minAno, Integer maxAno,Pageable paginacao
			);
	
	@Query(listaPrincipal + wherePaises)
	Page<ListaPibDto> paginar(
			List<Integer> idPaises, Integer minAno, 
			Integer maxAno, Pageable paginacao);
	
	public final String escolhaCo2PerCapita = """
			case when :isCo2PerCapita = true 
			then """ + QueriesConstantes.CALC_CO2_PER_CAPITA
			+"""
			else c.AnnualCo 
			end
			""";
	public final String calcMedias = """ 
			SELECT 
			 new br.univesp.analisedados.dto.responses.PibCo2DadosDto
			(p.id.year,
			cast(avg(p.totalGdp) as BigDecimal )
				,min(p.totalGdp),max(p.totalGdp
				),"""
			+"cast(avg(" 
				+ QueriesConstantes.CALC_PIB_PER_CAPITA 
			+") as BigDecimal )"
			+"	,min("
				+ QueriesConstantes.CALC_PIB_PER_CAPITA + " )"
			+"	,max("
				+ QueriesConstantes.CALC_PIB_PER_CAPITA
			+ """
				),
			cast(avg( 
			""" + escolhaCo2PerCapita +" ) as BigDecimal )" 
			+" ,min(" + escolhaCo2PerCapita + ")"
			+" ,max(" + escolhaCo2PerCapita
			+" ) )"
			+"""
			FROM Pib p
			LEFT JOIN TamanhoPopulacao t ON p.id = t.id
			LEFT JOIN Co2 c ON p.id = c.id
			WHERE 
			c.AnnualCo != 0 AND p.totalGdp != 0 AND 
			""" + whereAno;
	@Query(calcMedias + " group by p.id.year order by p.id.year")
	List<PibCo2DadosDto> mediaCo(
			Integer minAno, Integer maxAno, Boolean isCo2PerCapita
			);
	
	@Query(calcMedias + wherePaises + " group by p.id.year order by p.id.year")
	List<PibCo2DadosDto> mediaCo(
			List<Integer> idPaises, Integer minAno, Integer maxAno, Boolean isCo2PerCapita
			);
	
}
