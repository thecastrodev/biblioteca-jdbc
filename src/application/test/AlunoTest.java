package application.test;

import java.util.List;
import java.util.Scanner;

import db.DB;
import model.dao.AlunoDao;
import model.dao.DaoFactory;
import model.entities.Aluno;

public class AlunoTest {

	public void init() {
		AlunoDao alunoDao = DaoFactory.createAlunoDao();
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("=== TEST 1: autor findByCPF ===");
		Aluno aluno = alunoDao.findByCPF("12345678901");
		System.out.println(aluno);
		
		System.out.println("=== TEST 2: autor findAll ===");
		List<Aluno> list = alunoDao.findAll();
		for (Aluno obj : list) {
			System.out.println(obj);
		}
		
		scanner.close();
		DB.closeConnection();
	}

}
