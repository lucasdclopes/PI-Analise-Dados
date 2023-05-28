package br.univesp.analisedados.dto.queryparams;

import java.util.List;

import br.univesp.analisedados.exceptions.DadosInvalidosException;

public record PaisAnoParam(
		List<Integer> idPais,
		Integer minAno,
		Integer maxAno) {

	public void validarDados() {
		if (minAno == null || maxAno == null)
			return;
		if ( minAno >= maxAno)
			throw new DadosInvalidosException(
					"O ano máximo que você escolheu (" + maxAno + ") não pode ser menor que o ano mínimo escolhido (" + minAno +") "
					, "maxAno");
	}
}
