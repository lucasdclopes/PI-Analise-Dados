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

import br.univesp.analisedados.dto.responses.ListaPaisesDto;
import br.univesp.analisedados.exceptions.EntidadeNaoEncontradaException;
import br.univesp.analisedados.helpers.ControllerHelper;
import br.univesp.analisedados.repositorios.PaisRepository;

@RestController
@RequestMapping("/api/pais")
public class PaisController {
	
	@Autowired private PaisRepository paisDao;
	
	@GetMapping
	public ResponseEntity<List<ListaPaisesDto>> listar(
			@PageableDefault(sort = "country_name", direction = Direction.DESC, page = 0, size = 10) Pageable paginacao
			) throws EntidadeNaoEncontradaException{
			
		Page<ListaPaisesDto> pagina = paisDao.paginar(paginacao);
		if (pagina.hasContent()) {
			return ResponseEntity.ok().headers(ControllerHelper.adicionarHeaderPaginacao(pagina.getTotalPages(), pagina.hasNext())).body(pagina.getContent());
		}
		else
			throw new EntidadeNaoEncontradaException();
			
	}

}
