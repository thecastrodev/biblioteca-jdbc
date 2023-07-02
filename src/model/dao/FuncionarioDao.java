package model.dao;

import java.util.List;

import model.entities.Funcionario;

public interface FuncionarioDao {
	void insert(Funcionario obj);
	void update(Funcionario obj);
	void deleteByCPF(String CPF);
	Funcionario findByCPF(String CPF);
	List<Funcionario> findAll();
}
