package br.univesp.analisedados.dto.responses;

import java.math.BigDecimal;

import br.univesp.analisedados.dto.TendenciaCentralDto;

public record PibCo2DadosDto (Integer ano, TendenciaCentralDto pib, TendenciaCentralDto pibPerCap, TendenciaCentralDto co2) {
	public PibCo2DadosDto(Integer ano, 
			BigDecimal tendPib, BigDecimal pibMin,BigDecimal pibMax,
			BigDecimal tendPibPerCap, BigDecimal pibPerCapMin,BigDecimal pibPerCapMax,
			BigDecimal tendCo2, BigDecimal Co2Min,BigDecimal Co2Max){
		this(ano, 
				new TendenciaCentralDto(tendPib, pibMin,pibMax),
				new TendenciaCentralDto(tendPibPerCap, pibPerCapMin,pibPerCapMax),
				new TendenciaCentralDto(tendCo2, Co2Min,Co2Max) );
	}
	
}