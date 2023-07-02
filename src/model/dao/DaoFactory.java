package model.dao;

import db.DB;
import model.dao.impl.AutorDaoJDBC;
import model.dao.impl.CategoriaDaoJDBC;
import model.dao.impl.EditoraDaoJDBC;
import model.dao.impl.LivroDaoJDBC;

public class DaoFactory {

	public static CategoriaDao createCategoriaDao() {
		return new CategoriaDaoJDBC(DB.getConnection());
	}

	public static EditoraDao createEditoraDao() {
		return new EditoraDaoJDBC(DB.getConnection());
	}

	public static AutorDao createAutorDao() {
		return new AutorDaoJDBC(DB.getConnection());
	}

	public static LivroDao createLivroDao() {
		return new LivroDaoJDBC(DB.getConnection());
	}
}