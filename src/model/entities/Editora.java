package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Editora implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String codigo;
	private String nome;
	
	public Editora() {}
	
	public Editora(String codigo, String nome) {
		super();
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
		Editora other = (Editora) obj;
		return Objects.equals(codigo, other.codigo);
	}

	@Override
	public String toString() {
		return "Editora [codigo=" + codigo + ", nome=" + nome + "]";
	}
	
}
