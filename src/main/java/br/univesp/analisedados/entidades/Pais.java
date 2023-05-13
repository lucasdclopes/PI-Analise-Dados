package br.univesp.analisedados.entidades;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "countries")
public class Pais implements Serializable {
	
	private static final long serialVersionUID = 1L;
	  
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCountry;
	private String countryCode;
	private String countryName;
	private String subRegionName;
	private String incomeGroup;
	
	/**
	 * Construtor padrão da JPA. Não utilizar.
	 */
	@Deprecated
	public Pais() {}

	public Pais(String countryCode, String countryName, String subRegionName, String incomeGroup) {
		this.countryCode = countryCode;
		this.countryName = countryName;
		this.subRegionName = subRegionName;
		this.incomeGroup = incomeGroup;
	}

	public Integer getIdCountry() {
		return idCountry;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public String getCountryName() {
		return countryName;
	}

	public String getSubRegionName() {
		return subRegionName;
	}

	public String getIncomeGroup() {
		return incomeGroup;
	}	

}
