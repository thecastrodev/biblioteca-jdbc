package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Curso implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String codCurso;
	private String nomeCurso;
	
	public Curso( String codCurso, String nomeCurso) {
		this.codCurso = codCurso;
		this.nomeCurso = nomeCurso;
	}

	public String getCodCurso() {
		return codCurso;
	}

	public void setCodCurso(String codCurso) {
		this.codCurso = codCurso;
	}

	public String getNomeCurso() {
		return nomeCurso;
	}

	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codCurso);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Curso other = (Curso) obj;
		return Objects.equals(codCurso, other.codCurso);
	}

	@Override
	public String toString() {
		return "Curso [codCurso=" + codCurso + ", nomeCurso=" + nomeCurso + "]";
	}
}
