package br.univesp.analisedados.repositorios;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.univesp.analisedados.dto.responses.ListaPaisesDto;
import br.univesp.analisedados.entidades.Pais;

public interface PaisRepository extends JpaRepository<Pais, Integer> {

	boolean existsByCountryName(String countryName);
	
	@Query(""" 
			SELECT new br.univesp.analisedados.dto.responses.ListaPaisesDto 
			(idCountry,countryCode,countryName,subRegionName,incomeGroup) 
			FROM Pais p
			""")
	Page<ListaPaisesDto> paginar(Pageable paginacao);
}
