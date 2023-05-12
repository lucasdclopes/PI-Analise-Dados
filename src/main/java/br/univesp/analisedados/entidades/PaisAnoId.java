package br.univesp.analisedados.entidades;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class PaisAnoId implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer year;
	private Integer idCountry;
	public PaisAnoId() {} 
	public PaisAnoId(Integer year, Integer idCountry) {
		this.year = year;
		this.idCountry =idCountry;
	}
	
	
	public Integer getYear() {
		return year;
	}
	public Integer getIdCountry() {
		return idCountry;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(idCountry, year);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PaisAnoId other = (PaisAnoId) obj;
		return Objects.equals(idCountry, other.idCountry) && Objects.equals(year, other.year);
	}

}
