package model.dao;

import java.util.List;

import model.entities.Autor;

public interface AutorDao {
	void insert(Autor obj);
	void update(Autor obj);
	void deleteByEmail(String email);
	Autor findByEmail(String email);
	List<Autor> findAll();
}
