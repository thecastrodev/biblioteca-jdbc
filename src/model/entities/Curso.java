package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Curso implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String codigo;
	private String nome;
	
	public Curso() {}
	
	public Curso( String codigo, String nome) {
		this.codigo = codigo;
		this.nome = nome;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo);
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
		return Objects.equals(codigo, other.codigo);
	}

	@Override
	public String toString() {
		return "Curso [codigo=" + codigo + ", nome=" + nome + "]";
	}
}
