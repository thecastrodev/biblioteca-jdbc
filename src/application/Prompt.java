package application;

import java.util.List;

import iofunctions.Input;
import iofunctions.Output;
import model.dao.AlunoDao;
import model.dao.DaoFactory;
import model.dao.FuncionarioDao;
import model.dao.LivroDao;
import model.dao.ProfessorDao;
import model.dao.ReservaDao;
import model.entities.Aluno;
import model.entities.Funcionario;
import model.entities.Livro;
import model.entities.Professor;
import model.entities.Reserva;

public class Prompt {
	Output stdout;
	Input stdin;
	LivroDao livroDao = DaoFactory.createLivroDao();
	ReservaDao reservaDao = DaoFactory.createReservaDao();
	AlunoDao alunoDao = DaoFactory.createAlunoDao();
	ProfessorDao professorDao = DaoFactory.createProfessorDao();
	FuncionarioDao funcionarioDao = DaoFactory.createFuncionarioDao();

	public Prompt() {
		this.stdout = new Output();
		this.stdin = new Input();
	}

	public void iniciar() {
		menu();
	}

	public void menu() {
		int escolha = -1;
		String options[] = { "Adiministração", "Cliente", "Sair" };
		while (escolha != 3) {
			escolha = stdin.menu("Biblioteca UFC", options);
			switch (escolha) {
			case 1:
				String usuario = stdin.readString("Usuario: ");
				String senha = stdin.readString("Senha: ");
				if (usuario.equals("Admin") && senha.equals("Root")) {
					adm();
				} else {
					stdout.print("Usuario ou senha incorreto!");
					stdin.pause();
					menu();
				}
				
				break;
			case 2:
//				cliente();
				break;
			}
		}
		System.exit(1);
	}
	
	public void adm() {
		int escolha = admNav();
		if (escolha == 4) {
			menu();
		} else {
			admProceder(escolha);
		}

	}

	public int admNav() {
		int option = stdin.menu("Administração da Biblioteca",
				new String[] { "Controle de reservas", "Controle de Usuarios", "Controle de Estoque de Livros", "Sair" });
		return option;
	}
	
	public void admProceder(int escolha) {
		switch (escolha) {
		case 1:
			menuReservas();
			stdin.pause();
			break;
		case 2:
			menuControleUsuario();
			stdin.pause();
			break;
		case 3:
			menuControleEstoque();
			break;
		}
	}
	

	/* ===========================   CONTROLE DE RESERVAS   ============================== */
	
	public void menuReservas() {
		stdout.clear();
		int escolha = admNavReservas();
		if (escolha == 5) {
			menu();
		} else {
			admProcederReservas(escolha);
		}
	}
	
	public int admNavReservas() {
		int option = stdin.menu("Menu de Reservas",
				new String[] { "Reservar", "Devolver", "Listar Por CPF", "Listar Todos", "Sair" });
		return option;
	}
	
	public void admProcederReservas(int escolha) {
		switch (escolha) {
		case 1:
			reservarLivro();
			break;
		case 2:
			devolverLivro();
			break;
		case 3:
			listarLivroPorCPF();
			break;
		case 4:
			listarTodasAsReservas();
			break;
		}
		stdin.pause();
		menuReservas();
	}
	
	public void reservarLivro() { }
	
	public void devolverLivro() { }
	
	public void listarLivroPorCPF() {
		String cpf = stdin.readString("Digite o CPF do Usuario: ");
		List<Reserva> list = reservaDao.findAllByCPF(cpf);
		for (Reserva obj : list) {
			System.out.println(""
					+ "ISBN: " + obj.getLivro().getISBN()
					+ "\nCPF: " + obj.getUsuario().getCPF()
					+ "\nData de Reserva: " + obj.getDataDeReserva()
					+ "\n----------------------------");
		}
	}
	
	public void listarTodasAsReservas() {
		List<Reserva> list = reservaDao.findAll();
		for (Reserva obj : list) {
			System.out.println(""
					+ "ISBN: " + obj.getLivro().getISBN()
					+ "\nCPF: " + obj.getUsuario().getCPF()
					+ "\nData de Reserva: " + obj.getDataDeReserva()
					+ "\n----------------------------");
		}
	}
	
	/* =================================================================================== */
	
	public void menuControleUsuario() {
		stdout.clear();
		int escolha = admNavControleUsuario();
		if (escolha == 7) {
			menu();
		} else {
			admProcederControleUsuario(escolha);
		}
	}
	
	public int admNavControleUsuario() {
		int option = stdin.menu("Menu de Controle de Usuario",
				new String[] { "Cadastrar Aluno", "Cadastrar Professor", "Cadastrar Funcionario", 
						"Listar Alunos", "Listar Professores", "Listar Funcionarios", "Sair" });
		return option;
	}
	
	public void admProcederControleUsuario(int escolha) {
		switch (escolha) {
		case 1:
			cadastrarAluno();
			break;
		case 2:
			cadastrarProfessor();
			break;
		case 3:
			cadastrarFuncionario();
			break;
		case 4:
			listarTodosAlunos();
			break;
		case 5:
			listarTodosProfessores();
			break;
		case 6:
			listarTodosFuncionarios();
			break;
		}
		stdin.pause();
		menuControleUsuario();
	}
	
	public void cadastrarAluno() {}

	public void cadastrarProfessor() {}

	public void cadastrarFuncionario() {}

	public void listarTodosAlunos() {
		List<Aluno> list = alunoDao.findAll();
		System.out.println("----------------------------");
		for (Aluno aluno : list) {
			System.out.println(""
					+ "\n CPF: " + aluno.getCPF()
					+ "\n Nome: " + aluno.getNome()
					+ "\n Matricula: " + aluno.getMat()
					+ "\n----------------------------");
		}
	}

	public void listarTodosProfessores() {
		List<Professor> list = professorDao.findAll();
		System.out.println("----------------------------");
		for (Professor professor : list) {
			System.out.println(""
					+ "\n CPF: " + professor.getCPF()
					+ "\n Nome: " + professor.getNome()
					+ "\n Matricula Siape: " + professor.getMatSiape()
					+ "\n----------------------------");
		}
	}

	public void listarTodosFuncionarios() {
		List<Funcionario> list = funcionarioDao.findAll();
		System.out.println("----------------------------");
		for (Funcionario funcionario : list) {
			System.out.println(""
					+ "\n CPF: " + funcionario.getCPF()
					+ "\n Nome: " + funcionario.getNome()
					+ "\n Matricula: " + funcionario.getMat()
					+ "\n----------------------------");
		}
	}

	
/* ===========================   CONTROLE DE ESTOQUE   ============================== */
	
	public void menuControleEstoque() {
		stdout.clear();
		int escolha = admNavControleEstoque();
		if (escolha == 8) {
			menu();
		} else {
			admProcederControleEstoque(escolha);
		}
	}
	
	public int admNavControleEstoque() {
		int option = stdin.menu("Menu de Controle de Estoque",
				new String[] { "Cadastrar Livro", "Apagar Livro", "Procurar por ISBN", "Listar Todos", 
						"Listar Por Categoria", "Listar Por Editora", "Listar Por Autor", "Sair" });
		return option;
	}
	
	public void admProcederControleEstoque(int escolha) {
		switch (escolha) {
		case 1:
			cadastrarLivro();
			break;
		case 2:
			apagarLivro();
			break;
		case 3:
			procurarPorISBN();
			break;
		case 4:
			listarTodosLivros();
			break;
		case 5:
			listarLivrosPorCategoria();
			break;
		case 6:
			listarLivrosPorEditora();
			break;
		case 7:
			listarLivrosPorAutor();
			break;
		}
		stdin.pause();
		menuControleEstoque();
	}
	
	public void cadastrarLivro() {}
	
	public void apagarLivro() {}
	
	public void procurarPorISBN() {
		String isbn = stdin.readString("Digite o ISBN: ");
		Livro livro = livroDao.findByISBN(isbn);
		System.out.println("----------------------------");
		System.out.println(""
				+ "\n ISBN: " + livro.getISBN()
				+ "\n Titulo: " + livro.getTitulo()
				+ "\n Ano de Lancamento: " + livro.getAnoDeLancamento()
				+ "\n Editora: " + livro.getEditora().getNome()
				+ "\n Categoria: " + livro.getCategoria().getNome()
				+ "\n----------------------------");
	}
	
	public void listarTodosLivros() {
		List<Livro> list = livroDao.findAll();
		System.out.println("----------------------------");
		for (Livro livro : list) {
			System.out.println(""
					+ "\n ISBN: " + livro.getISBN()
					+ "\n Titulo: " + livro.getTitulo()
					+ "\n Ano de Lancamento: " + livro.getAnoDeLancamento()
					+ "\n Editora: " + livro.getEditora().getNome()
					+ "\n Categoria: " + livro.getCategoria().getNome()
					+ "\n----------------------------");
		}
	}
	
	public void listarLivrosPorCategoria() {
		String categoria = stdin.readString("Digite uma categoria: ");
		List<Livro> list = livroDao.findByCategoria(categoria);
		System.out.println("----------------------------");
		for (Livro livro : list) {
			System.out.println(""
					+ "\n ISBN: " + livro.getISBN()
					+ "\n Titulo: " + livro.getTitulo()
					+ "\n Ano de Lancamento: " + livro.getAnoDeLancamento()
					+ "\n Editora: " + livro.getEditora().getNome()
					+ "\n Categoria: " + livro.getCategoria().getNome()
					+ "\n----------------------------");
		}
	}
	
	public void listarLivrosPorEditora() {
		String editora = stdin.readString("Digite uma editora: ");
		List<Livro> list = livroDao.findByEditora(editora);
		System.out.println("----------------------------");
		for (Livro livro : list) {
			System.out.println(""
					+ "\n ISBN: " + livro.getISBN()
					+ "\n Titulo: " + livro.getTitulo()
					+ "\n Ano de Lancamento: " + livro.getAnoDeLancamento()
					+ "\n Editora: " + livro.getEditora().getNome()
					+ "\n Categoria: " + livro.getCategoria().getNome()
					+ "\n----------------------------");
		}
	}
	
	public void listarLivrosPorAutor() {
		String autor = stdin.readString("Digite uma autor: ");
		List<Livro> list = livroDao.findByAutor(autor);
		System.out.println("----------------------------");
		for (Livro livro : list) {
			System.out.println(""
					+ "\n ISBN: " + livro.getISBN()
					+ "\n Titulo: " + livro.getTitulo()
					+ "\n Ano de Lancamento: " + livro.getAnoDeLancamento()
					+ "\n Editora: " + livro.getEditora().getNome()
					+ "\n Categoria: " + livro.getCategoria().getNome()
					+ "\n----------------------------");
		}
	}
	
	/* =================================================================================== */
	
	
}
