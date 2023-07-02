package model.dao;

import java.util.List;

import model.entities.Aluno;

public interface AlunoDao {
	void insert(Aluno obj);
	void update(Aluno obj);
	void deleteByCPF(String CPF);
	Aluno findByCPF(String CPF);
	List<Aluno> findAll();
}
