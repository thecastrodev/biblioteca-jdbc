package model.dao;

import java.util.List;

import model.entities.Categoria;
import model.entities.Editora;
import model.entities.Livro;

public interface LivroDao {
	void insert(Livro obj);
	void update(Livro obj);
	void deleteByISBN(String ISBN);
	Livro findByISBN(String ISBN);
	List<Livro> findAll();
	List<Livro> findByCateogria(Categoria categoria);
	List<Livro> findByEditora(Editora editora);
	List<Livro> findByAutor(Editora editora);
}
