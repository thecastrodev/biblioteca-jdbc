package application.test;

import java.util.List;
import java.util.Scanner;

import db.DB;
import model.dao.DaoFactory;
import model.dao.LivroDao;
import model.entities.Livro;

public class LivroTest {

	public void init() {
		LivroDao livroDao = DaoFactory.createLivroDao();
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("=== TEST 1: autor findByCode ===");
		Livro livro = livroDao.findByISBN("ISBN1");
		System.out.println(livro);
		
		System.out.println("=== TEST 2: autor findAll ===");
		List<Livro> list = livroDao.findAll();
		for (Livro obj : list) {
			System.out.println(obj);
		}
		
		scanner.close();
		DB.closeConnection();
	}

}
