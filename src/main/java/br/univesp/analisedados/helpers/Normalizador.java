package br.univesp.analisedados.helpers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

import br.univesp.analisedados.dto.TendenciaCentralDto;
import br.univesp.analisedados.dto.responses.PibCo2DadosDto;

public class Normalizador {

	private BigDecimal normalizar(BigDecimal ponto, BigDecimal min, BigDecimal max) {
		return ( ponto.subtract(min) ).divide((max.subtract(min)),4,RoundingMode.HALF_UP);
	}
	public List<PibCo2DadosDto> normalizarPibCo2(List<PibCo2DadosDto> entrada) {
		BigDecimal minCo2 = getMinPibCo2(entrada,e -> e.co2().tendenciaCentral());
		BigDecimal maxCo2 = getMaxPibCo2(entrada,e -> e.co2().tendenciaCentral());
		
		BigDecimal minPib = getMinPibCo2(entrada,e -> e.pib().tendenciaCentral());
		BigDecimal maxPib = getMaxPibCo2(entrada,e -> e.pib().tendenciaCentral());
		
		BigDecimal minPibPerCap = getMinPibCo2(entrada,e -> e.pibPerCap().tendenciaCentral());
		BigDecimal maxPibPerCap = getMaxPibCo2(entrada,e -> e.pibPerCap().tendenciaCentral());
		
		List<PibCo2DadosDto> dadosNormalizados = new ArrayList<>();
		entrada.forEach(e -> {
			TendenciaCentralDto pib = e.pib();
			TendenciaCentralDto pibPerCap = e.pibPerCap();
			TendenciaCentralDto co2 = e.co2();
			
			dadosNormalizados.add(new PibCo2DadosDto(
					e.ano(), 
					new TendenciaCentralDto(normalizar(pib.tendenciaCentral(),minPib,maxPib),pib.min(),pib.max()), 
					new TendenciaCentralDto(normalizar(pibPerCap.tendenciaCentral(),minPibPerCap,maxPibPerCap),pibPerCap.min(),pibPerCap.max()), 
					new TendenciaCentralDto(normalizar(co2.tendenciaCentral(),minCo2,maxCo2),co2.min(),co2.max())
					));
		});
	
		return dadosNormalizados;

	}
	
	public BigDecimal getMinPibCo2(List<PibCo2DadosDto> entrada,Function<? super PibCo2DadosDto, ? extends BigDecimal> mapper ) {
		return entrada.stream().map(mapper).min(Comparator.naturalOrder()).get();
	}
	public BigDecimal getMaxPibCo2(List<PibCo2DadosDto> entrada,Function<? super PibCo2DadosDto, ? extends BigDecimal> mapper ) {
		return entrada.stream().map(mapper).max(Comparator.naturalOrder()).get();
	}
	//public Double getMinPibCo2(List<PibCo2DadosDto> entrada,ToDoubleFunction<? super PibCo2DadosDto> mapper) {
		//return entrada.stream().mapToDouble(mapper).min().getAsDouble();
	//}
	//public Double getMaxPibCo2(List<PibCo2DadosDto> entrada,ToDoubleFunction<? super PibCo2DadosDto> mapper) {
		//return entrada.stream().mapToDouble(mapper).max().getAsDouble();
	//}
}
