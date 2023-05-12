package br.univesp.analisedados.dto.responses;

import java.math.BigDecimal;

public record ListaPibDto(
		 Integer Ano,Integer idPais, 
		 BigDecimal pibTotal, BigDecimal pibTotalMilhao, BigDecimal variacaoPib, BigDecimal pibPerCapita
		 ) {
}
