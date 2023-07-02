package application.test;

import java.util.List;
import java.util.Scanner;

import db.DB;
import model.dao.DaoFactory;
import model.dao.EditoraDao;
import model.entities.Editora;

public class EditoraTest {

	public void init() {
		EditoraDao editoraDao = DaoFactory.createEditoraDao();
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("=== TEST 1: editora findByCode ===");
		Editora editora = editoraDao.findByCode("EDT1");
		System.out.println(editora);
		
		System.out.println("=== TEST 2: editora findAll ===");
		List<Editora> list = editoraDao.findAll();
		for (Editora obj : list) {
			System.out.println(obj);
		}
		
//		System.out.println("=== TEST 4: editora insert ===");
//		Editora novaEditora = new Editora(null, "Jogos");
//		editoraDao.insert(novaEditora);
//		System.out.println("Inserted! New code = " + novaEditora.getCodigo());
	
//		System.out.println("=== TEST 5: editora update ===");
//		editora = editoraDao.findByCode("CAT3");
//		editora.setNome("Ficção");
//		editoraDao.update(editora);
//		System.out.println("Updated!");		

//		System.out.println("=== TEST 6: editora delete ===");
//		System.out.print("Enter code for delete: ");
//		String code = scanner.nextLine();
//		editoraDao.deleteByCode(code);
//		System.out.println("\nDelete completed!");
		
		scanner.close();
		DB.closeConnection();
	}

}
