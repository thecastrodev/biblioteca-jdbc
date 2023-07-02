package application.test;

import java.util.List;
import java.util.Scanner;

import db.DB;
import model.dao.CategoriaDao;
import model.dao.DaoFactory;
import model.entities.Categoria;

public class CategoriaTest {

	public void init() {
		CategoriaDao categoriaDao = DaoFactory.createCategoriaDao();
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("=== TEST 1: categoria findByCode ===");
		Categoria categoria = categoriaDao.findByCode("CAT1");
		System.out.println(categoria);
		
		System.out.println("=== TEST 2: categoria findAll ===");
		List<Categoria> list = categoriaDao.findAll();
		for (Categoria obj : list) {
			System.out.println(obj);
		}
		
//		System.out.println("=== TEST 4: categoria insert ===");
//		Categoria novaCategoria = new Categoria(null, "Jogos");
//		categoriaDao.insert(novaCategoria);
//		System.out.println("Inserted! New code = " + novaCategoria.getCodigo());
	
//		System.out.println("=== TEST 5: categoria update ===");
//		categoria = categoriaDao.findByCode("CAT3");
//		categoria.setNome("Ficção");
//		categoriaDao.update(categoria);
//		System.out.println("Updated!");
		

//		System.out.println("=== TEST 6: categoria delete ===");
//		System.out.print("Enter code for delete: ");
//		String code = scanner.nextLine();
//		categoriaDao.deleteByCode(code);
//		System.out.println("\nDelete completed!");
		
		scanner.close();
		DB.closeConnection();
	}

}
