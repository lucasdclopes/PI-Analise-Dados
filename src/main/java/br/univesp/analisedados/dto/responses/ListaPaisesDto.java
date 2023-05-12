package br.univesp.analisedados.dto.responses;

public record ListaPaisesDto(
		 Long idCountry,String countryCode,String countryName,String subRegionName,String incomeGroup
		 ) {
}
