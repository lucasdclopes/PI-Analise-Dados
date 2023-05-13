package br.univesp.analisedados.entidades;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity()
@Table(name = "population_size")
public class TamanhoPopulacao implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private PaisAnoId id = new PaisAnoId();
	
	private Long populationEst;
	
	/**
	 * Construtor padrão da JPA. Não utilizar.
	 */
	@Deprecated
	public TamanhoPopulacao() {}
	
	public TamanhoPopulacao(Integer year, Integer idCountry, Long populationEst) {
		this.id = new PaisAnoId(year, idCountry);
		this.populationEst = populationEst;
	}
	public PaisAnoId getId() {
		return id;
	}
	
	public Long getPopulationEst() {
		return populationEst;
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
		TamanhoPopulacao other = (TamanhoPopulacao) obj;
		return Objects.equals(id, other.id);
	}

}
