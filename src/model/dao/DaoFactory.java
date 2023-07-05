package model.dao;

import db.DB;
import model.dao.impl.*;

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
	
	public static AlunoDao createAlunoDao() {
		return new AlunoDaoJDBC(DB.getConnection());
	}
	
	public static ProfessorDao createProfessorDao() {
		return new ProfessorDaoJDBC(DB.getConnection());
	}
	
	public static FuncionarioDao createFuncionarioDao() {
		return new FuncionarioDaoJDBC(DB.getConnection());
	}
	
	public static CursoDao createCursoDao() {
		return new CursoDaoJDBC(DB.getConnection());
	}
	
	public static ReservaDao createReservaDao() {
		return new ReservaDaoJDBC(DB.getConnection());
	}
}