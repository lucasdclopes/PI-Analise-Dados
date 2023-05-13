package br.univesp.analisedados.dto.queryparams;

import java.util.List;

public record PaisAnoParam(
		List<Integer> idPais,
		Integer minAno,
		Integer maxAno) {

}
