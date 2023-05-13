package br.univesp.analisedados.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity()
@Table(name = "annual_co")
public class Co2 implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private PaisAnoId id = new PaisAnoId();
	
	private BigDecimal AnnualCo;
	
	/**
	 * Construtor padrão da JPA. Não utilizar.
	 */
	@Deprecated
	public Co2() {}
	
	public Co2(Integer year, Integer idCountry, BigDecimal AnnualCo) {
		this.id = new PaisAnoId(year, idCountry);
		this.AnnualCo = AnnualCo;
	}
	public PaisAnoId getId() {
		return id;
	}
	
	public BigDecimal getAnnualCo() {
		return AnnualCo;
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
		Co2 other = (Co2) obj;
		return Objects.equals(id, other.id);
	}

}
