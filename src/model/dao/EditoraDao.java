package model.dao;

import java.util.List;

import model.entities.Editora;

public interface EditoraDao {
	void insert(Editora obj);
	void update(Editora obj);
	void deleteByCode(String codigo);
	Editora findByCode(String codigo);
	List<Editora> findAll();
}
