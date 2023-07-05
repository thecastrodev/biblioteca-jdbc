package application.test;

import java.util.List;
import java.util.Scanner;

import db.DB;
import model.dao.DaoFactory;
import model.dao.FuncionarioDao;
import model.entities.Funcionario;

public class FuncionarioTest {

	public void init() {
		FuncionarioDao funcionarioDao = DaoFactory.createFuncionarioDao();
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("=== TEST 1: autor findByCPF ===");
		Funcionario funcionario = funcionarioDao.findByCPF("12345678906");
		System.out.println(funcionario);
		
		System.out.println("=== TEST 2: autor findAll ===");
		List<Funcionario> list = funcionarioDao.findAll();
		for (Funcionario obj : list) {
			System.out.println(obj);
		}
		
		scanner.close();
		DB.closeConnection();
	}

}
