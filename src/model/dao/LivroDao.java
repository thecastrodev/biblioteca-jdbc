package model.dao;

import java.util.List;

import model.entities.Livro;

public interface LivroDao {
	void insert(Livro obj);
	void update(Livro obj);
	void deleteByISBN(String ISBN);
	Livro findByISBN(String ISBN);
	List<Livro> findAll();
	List<Livro> findByCategoria(String categoria);
	List<Livro> findByEditora(String editora);
	List<Livro> findByAutor(String autor);
	List<Livro> findByAnoDeLancamento(String anoDeLancamento);
}
