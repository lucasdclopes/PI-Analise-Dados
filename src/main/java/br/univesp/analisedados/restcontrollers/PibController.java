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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.univesp.analisedados.dto.responses.ListaPibDto;
import br.univesp.analisedados.exceptions.EntidadeNaoEncontradaException;
import br.univesp.analisedados.helpers.ControllerHelper;
import br.univesp.analisedados.repositorios.PibRepository;

@RestController
@RequestMapping("/api/pib")
public class PibController {
	
	@Autowired private PibRepository pibDao;
	
	@GetMapping
	public ResponseEntity<List<ListaPibDto>> listar(
			@PageableDefault(sort = {"id.idCountry","id.year"}, direction = Direction.ASC, page = 0, size = 10) Pageable paginacao,
			@RequestParam(required = false) List<Integer> idPais
			) throws EntidadeNaoEncontradaException{
			
		//Page<ListaPibDto> pagina = pibDao.paginar(paginacao);
		Page<ListaPibDto> pagina = idPais == null? pibDao.paginar(paginacao): pibDao.paginar(idPais,paginacao);
		if (pagina.hasContent()) {
			return ResponseEntity.ok().headers(ControllerHelper.adicionarHeaderPaginacao(pagina.getTotalPages(), pagina.hasNext())).body(pagina.getContent());
		}
		else
			throw new EntidadeNaoEncontradaException();
			
	}
}
