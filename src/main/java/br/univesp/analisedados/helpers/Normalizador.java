package br.univesp.analisedados.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.function.ToDoubleFunction;

import br.univesp.analisedados.dto.TendenciaCentralDto;
import br.univesp.analisedados.dto.responses.PibCo2DadosDto;

public class Normalizador {

	private Double normalizar(Double ponto, Double min, Double max) {
		return ( ponto - min ) / (max - min);
	}
	public List<PibCo2DadosDto> normalizarPibCo2(List<PibCo2DadosDto> entrada) {
		Double minCo2 = getMinPibCo2(entrada,e -> e.co2().min().doubleValue());
		Double maxCo2 = getMaxPibCo2(entrada,e -> e.co2().max().doubleValue());
		
		Double minPib = getMinPibCo2(entrada,e -> e.pib().min().doubleValue());
		Double maxPib = getMaxPibCo2(entrada,e -> e.pib().max().doubleValue());
		
		Double minPibPerCap = getMinPibCo2(entrada,e -> e.pibPerCap().min().doubleValue());
		Double maxPibPerCap = getMaxPibCo2(entrada,e -> e.pibPerCap().max().doubleValue());
		
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
	public Double getMinPibCo2(List<PibCo2DadosDto> entrada,ToDoubleFunction<? super PibCo2DadosDto> mapper) {
		return entrada.stream().mapToDouble(mapper).min().getAsDouble();
	}
	public Double getMaxPibCo2(List<PibCo2DadosDto> entrada,ToDoubleFunction<? super PibCo2DadosDto> mapper) {
		return entrada.stream().mapToDouble(mapper).max().getAsDouble();
	}
}
