package br.univesp.analisedados.helpers;

import java.math.BigDecimal;
import java.math.RoundingMode;

import br.univesp.analisedados.dto.TendenciaCentralDto;
import br.univesp.analisedados.dto.responses.PibCo2DadosDto;

public class Normalizador {

	private TendenciaCentralDto normalizar(TendenciaCentralDto entrada) {
		BigDecimal range = entrada.max().subtract(entrada.min());
		BigDecimal ponto = BigDecimal.valueOf(entrada.tendenciaCentral());
		BigDecimal normalizado = (ponto.subtract(entrada.min())).divide(range,5,RoundingMode.HALF_UP);
		return new TendenciaCentralDto(normalizado.doubleValue(),entrada.min(),entrada.max());
		
	}
	public PibCo2DadosDto normalizarPibCo2(PibCo2DadosDto entrada) {
		TendenciaCentralDto co2 = normalizar(entrada.co2());
		TendenciaCentralDto pib = normalizar(entrada.pib());
		TendenciaCentralDto pibPerCap = normalizar(entrada.pibPerCap());
	
		return new PibCo2DadosDto(entrada.ano(), pib, pibPerCap, co2);

	}
}
