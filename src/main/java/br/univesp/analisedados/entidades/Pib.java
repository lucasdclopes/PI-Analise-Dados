package br.univesp.analisedados.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity()
@Table(name = "countries_gdp")
public class Pib implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private PaisAnoId id = new PaisAnoId();
	
	private BigDecimal totalGdp;
	private BigDecimal totalGdpMillion;
	private BigDecimal gdpVariation;
	
	/**
	 * Construtor padrão da JPA. Não utilizar.
	 */
	@Deprecated
	public Pib() {}
	
	public Pib(Integer year, Integer idCountry, BigDecimal totalGdp, BigDecimal totalGdpMillion,
			BigDecimal gdpVariation) {
		this.id = new PaisAnoId(year, idCountry);
		this.totalGdp = totalGdp;
		this.totalGdpMillion = totalGdpMillion;
		this.gdpVariation = gdpVariation;
	}
	public PaisAnoId getId() {
		return id;
	}
	public BigDecimal getTotalGdp() {
		return totalGdp;
	}
	public BigDecimal getTotalGdpMillion() {
		return totalGdpMillion;
	}
	public BigDecimal getGdpVariation() {
		return gdpVariation;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pib other = (Pib) obj;
		return Objects.equals(id, other.id);
	}

}
