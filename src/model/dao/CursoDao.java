package model.dao;

import java.util.List;

import model.entities.Curso;

public interface CursoDao {
		void insert(Curso obj);
		void update(Curso obj);
		void deleteByCode(String codigo);
		Curso findByCode(String codigo);
		List<Curso> findAll();
}
