package model.dao;

import java.util.List;

import model.entities.Professor;

public interface ProfessorDao {
	void insert(Professor obj);
	void update(Professor obj);
	void deleteByCPF(String CPF);
	Professor findByCPF(String CPF);
	List<Professor> findAll();
}
