package model.dao;

import java.util.List;

import model.entities.Categoria;

public interface CategoriaDao {
	void insert(Categoria obj);
	void update(Categoria obj);
	void deleteByCode(String codigo);
	Categoria findByCode(String codigo);
	List<Categoria> findAll();
}
