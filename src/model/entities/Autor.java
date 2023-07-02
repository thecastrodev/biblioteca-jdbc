package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Autor  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String email;
	private String nacionalidade;
	
	public Autor() {}
	
	public Autor(String nome, String email, String nacionalidade) {
		this.nome = nome;
		this.email = email;
		this.nacionalidade = nacionalidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Autor other = (Autor) obj;
		return Objects.equals(email, other.email);
	}

	@Override
	public String toString() {
		return "Autor [nome=" + nome + ", email=" + email + ", nacionalidade=" + nacionalidade + "]";
	}
	
}
