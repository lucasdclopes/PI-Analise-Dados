package br.univesp.analisedados.dto;

import java.math.BigDecimal;

public record TendenciaCentralDto (BigDecimal tendenciaCentral,BigDecimal min, BigDecimal max, BigDecimal tendenciaCentralNormalizado) {
	
}
 