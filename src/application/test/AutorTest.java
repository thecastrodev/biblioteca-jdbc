package application.test;

import java.util.List;
import java.util.Scanner;

import db.DB;
import model.dao.AutorDao;
import model.dao.DaoFactory;
import model.entities.Autor;

public class AutorTest {

	public void init() {
		AutorDao autorDao = DaoFactory.createAutorDao();
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("=== TEST 1: autor findByCode ===");
		Autor autor = autorDao.findByEmail("autor1@example.com");
		System.out.println(autor);
		
		System.out.println("=== TEST 2: autor findAll ===");
		List<Autor> list = autorDao.findAll();
		for (Autor obj : list) {
			System.out.println(obj);
		}
		
		scanner.close();
		DB.closeConnection();
	}

}
