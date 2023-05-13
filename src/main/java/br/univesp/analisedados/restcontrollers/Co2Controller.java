package br.univesp.analisedados.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.univesp.analisedados.dto.queryparams.PaisAnoParam;
import br.univesp.analisedados.dto.responses.ListaCo2Dto;
import br.univesp.analisedados.exceptions.EntidadeNaoEncontradaException;
import br.univesp.analisedados.helpers.ControllerHelper;
import br.univesp.analisedados.repositorios.Co2Repository;

@RestController
@RequestMapping("/api/co2")
public class Co2Controller {
	
	@Autowired private Co2Repository co2Dao;
	
	@GetMapping
	public ResponseEntity<List<ListaCo2Dto>> listar(
			@PageableDefault(sort = {"id.idCountry","id.year"}, direction = Direction.ASC, page = 0, size = 10) Pageable paginacao,
			PaisAnoParam params
			) throws EntidadeNaoEncontradaException{
			
		//Page<ListaPibDto> pagina = pibDao.paginar(paginacao);
		Page<ListaCo2Dto> pagina = params != null && params.idPais() != null ? 
				co2Dao.paginar(params.idPais(),params.minAno(),params.maxAno(),paginacao):
					co2Dao.paginar(params.minAno(),params.maxAno(),paginacao);
		if (pagina.hasContent()) {
			return ResponseEntity.ok().headers(ControllerHelper.adicionarHeaderPaginacao(pagina.getTotalPages(), pagina.hasNext())).body(pagina.getContent());
		}
		else
			throw new EntidadeNaoEncontradaException();
			
	}
	
	
}
