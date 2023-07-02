package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Livro implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String ISBN;
	private String titulo;
	private String anoDeLancamento;
	private Integer quantidadeDeCopias;
	private Editora editora;
	private Categoria categoria;
	
	public Livro() {}
	
	public Livro(String iSBN, String titulo, String anoDeLancamento, Editora editora, Categoria categoria) {
		ISBN = iSBN;
		this.titulo = titulo;
		this.anoDeLancamento = anoDeLancamento;
		this.editora = editora;
		this.categoria = categoria;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAnoDeLancamento() {
		return anoDeLancamento;
	}

	public void setAnoDeLancamento(String anoDeLancamento) {
		this.anoDeLancamento = anoDeLancamento;
	}
	
	public Integer getQuantidadeDeCopias() {
		return quantidadeDeCopias;
	}

	public void setQuantidadeDeCopias(Integer quantidadeDeCopias) {
		this.quantidadeDeCopias = quantidadeDeCopias;
	}

	public Editora getEditora() {
		return editora;
	}

	public void setEditora(Editora editora) {
		this.editora = editora;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ISBN);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Livro other = (Livro) obj;
		return Objects.equals(ISBN, other.ISBN);
	}

	@Override
	public String toString() {
		return "Livro [ISBN=" + ISBN + ", titulo=" + titulo + ", anoDeLancamento=" + anoDeLancamento + ", editora="
				+ editora + ", categoria=" + categoria + "]";
	}
}
