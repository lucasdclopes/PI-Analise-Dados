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
import br.univesp.analisedados.dto.responses.ListaPibDto;
import br.univesp.analisedados.dto.responses.PibCo2DadosDto;
import br.univesp.analisedados.exceptions.EntidadeNaoEncontradaException;
import br.univesp.analisedados.helpers.ControllerHelper;
import br.univesp.analisedados.helpers.Normalizador;
import br.univesp.analisedados.repositorios.PibRepository;

@RestController
@RequestMapping("/api/pib")
public class PibController {
	
	@Autowired private PibRepository pibDao;
	
	@GetMapping
	public ResponseEntity<List<ListaPibDto>> listar(
		@PageableDefault(sort = {"id.idCountry","id.year"}, 
			direction = Direction.ASC, page = 0, size = 10) Pageable paginacao,
		PaisAnoParam params
			) throws EntidadeNaoEncontradaException{
			
		params.validarDados();
		Page<ListaPibDto> pagina = params != null && params.idPais() != null ? 
				pibDao.paginar(
						params.idPais(),params.minAno(),params.maxAno(),paginacao
						):
					pibDao.paginar(params.minAno(),params.maxAno(),paginacao);
		if (pagina.hasContent()) {
			return ResponseEntity.ok()
					.headers(
						ControllerHelper
						.adicionarHeaderPaginacao(
								pagina.getTotalPages(), pagina.hasNext())
						)
					.body(pagina.getContent());
		}
		else
			throw new EntidadeNaoEncontradaException();
			
	}
	
	
	@GetMapping(path = "/calc-co2")
	public ResponseEntity<List<PibCo2DadosDto>> calcularCo(
		PaisAnoParam params,
		Boolean isCo2PerCapita
			) throws EntidadeNaoEncontradaException{
			
		params.validarDados();
		//Page<ListaPibDto> pagina = pibDao.paginar(paginacao);
		List<PibCo2DadosDto> dados = params != null && params.idPais() != null ? 
				pibDao.mediaCo(params.idPais(),params.minAno(),params.maxAno(),isCo2PerCapita):
					pibDao.mediaCo(params.minAno(),params.maxAno(),isCo2PerCapita);
		

		Normalizador norm = new Normalizador();
		dados = norm.normalizarPibCo2(dados) ;
		if (!dados.isEmpty()) {
			
			return ResponseEntity.ok().body(dados);
		}
		else
			throw new EntidadeNaoEncontradaException();
			
	}
}
