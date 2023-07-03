package application.test;

import java.util.List;
import java.util.Scanner;

import db.DB;
import model.dao.CursoDao;
import model.dao.DaoFactory;
import model.entities.Curso;

public class CursoTest {

	public void init() {
		CursoDao cursoDao = DaoFactory.createCursoDao();
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("=== TEST 1: autor findByCode ===");
		Curso curso = cursoDao.findByCode("COD1");
		System.out.println(curso);
		
		System.out.println("=== TEST 2: autor findAll ===");
		List<Curso> list = cursoDao.findAll();
		for (Curso obj : list) {
			System.out.println(obj);
		}
		
		scanner.close();
		DB.closeConnection();
	}

}
