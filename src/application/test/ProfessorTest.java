package application.test;

import java.util.List;
import java.util.Scanner;

import db.DB;
import model.dao.DaoFactory;
import model.dao.ProfessorDao;
import model.entities.Professor;

public class ProfessorTest {

	public void init() {
		ProfessorDao professorDao = DaoFactory.createProfessorDao();
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("=== TEST 1: autor findByCPF ===");
		Professor professor = professorDao.findByCPF("12345678902");
		System.out.println(professor);
		
		System.out.println("=== TEST 2: autor findAll ===");
		List<Professor> list = professorDao.findAll();
		for (Professor obj : list) {
			System.out.println(obj);
		}
		
		scanner.close();
		DB.closeConnection();
	}

}
