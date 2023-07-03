package application;

import java.util.Scanner;

public class Acessos { 

	Scanner scanner = new Scanner(System.in);

	public Acessos() {
		int escolha = 0;
		System.out.println("=== BEM-VINDO A BIBLIOTECA === \n" + "1 - Login \n" + "2 - Cadastrar Novo Usuario \n"
				+ "3 - Sair" + "\n=============================== ");
		escolha = scanner.nextInt();

		// forcar a escolha de uma opcao
		while (escolha < 1 || escolha > 3) {
			System.out.println("Escolha uma opcao valida:");
			escolha = scanner.nextInt();
		}

		switch (escolha) {
		case 1:
			login();
			break;
		case 2:
			cadastro();
			break;
		case 3:
			System.exit(1);
			break;
		}
	}

	public void login() {
		String CPF;
		String senha;

		System.out.println("======Login=======");
		System.out.println("Digite o seu Login:");
		CPF = scanner.next();
		// verificar no banco se tem um usuario com esse CPF

		System.out.println("Digite a sua senha:");
		senha = scanner.next();
		// verificar no banco se tem um usuario com essa senha
		System.out.println("================================");

		// se estiver tudo ok, armazenar as informacoes do usuario pesquisado no banco
		// em um usuarioAtualLogado, logar na aplicacao
	}

	public void cadastro() {
		String CPF = "";
		String senha = "";
		String endereco = "";
		String nome = "";

		System.out.println("=======Cadastro=======");
		System.out.println("Digite seu CPF:");
		CPF = verificaCPFcorreto(CPF);
		System.out.println("Digite sua senha:");
		senha = verificaFormatacaoCorreta(senha, 30);
		System.out.println("Digite o seu nome:");
		nome = verificaFormatacaoCorreta(nome, 45);
		System.out.println("Digite seu endere�o:");
		endereco = verificaFormatacaoCorreta(endereco, 50);

		criarUsuario(CPF, senha, endereco, nome);
	}

	public void criarUsuario(String CPF, String senha, String endereco, String nome) {
		System.out.println("Qual o seu tipo de usuario? \n1 - Professor \n2 - Funcionario \n3 - Aluno \n");
		int escolha = 0;
		do {
			escolha = scanner.nextInt();
			if (escolha < 1 || escolha > 3) {
				System.out.println("Digite uma opcao valida:");
			}
		} while (escolha < 1 || escolha > 3);

		// criar um usuario dependendo da escolha

		switch (escolha) {
		case 1:
			String matSiape = "";
			int regimeDeTrabalho = 0;
			System.out.println("Escreva sua matSiape:");
			matSiape = verificaFormatacaoCorreta(matSiape, 10);
			System.out.println("Qual o seu regime de trabalho? \n1 - 20h \n2 - 40h \n3 - DE");
			do {
				regimeDeTrabalho = scanner.nextInt();
				if (regimeDeTrabalho < 1 || regimeDeTrabalho > 3) {
					System.out.println("Digite uma opcao valida:");
				}
			} while (regimeDeTrabalho < 1 || regimeDeTrabalho > 3);

			// falta determinar a data
			// criar um usuario professor entregando nome, cpf, endereco, senha, matSiape,
			// regimeDeTrabalho e dataDeContratacao
			break;
		case 2:
			String matFuncionario = "";
			System.out.println("Escreva sua mat:");
			matSiape = verificaFormatacaoCorreta(matFuncionario, 10);
			// criar um usuario professor entregando nome, cpf, endereco, senha e
			// matFuncionario
			break;
		case 3:
			String mat = "";
			System.out.println("Escreva sua mat:");
			matSiape = verificaFormatacaoCorreta(mat, 6);

			// falta determinar a data
			// criar um usuario professor entregando nome, cpf, endereco, senha, mat,
			// dataDeIngresso e dataDePrevisaoDeConclusao
			break;
		}
	}

	public String verificaCPFcorreto(String CPF) {
		boolean correto = true;
		do {
			CPF = scanner.next();
			if (verificaPossuiTamanhoCorreto(CPF, 11, true)) {
				if (verificaContemApenasNumeros(CPF)) {
					correto = false;
				} else {
					System.out.println("O CPF deve conter apenas digitos numericos, escreva novamente:");
				}
			} else {
				System.out.println("O CPF deve conter 11 digitos numericos, escreva novamente:");
			}
		} while (correto);
		return CPF;
	}

	public String verificaFormatacaoCorreta(String palavra, int tamanho) {
		boolean correto = true;
		do {
			palavra = scanner.next();
			if (verificaPossuiTamanhoCorreto(palavra, tamanho, false)) {
				correto = false;
				if ((tamanho == 6 || tamanho == 10) && !verificaContemApenasNumeros(palavra)) {
					correto = true;
					System.out.println("A palavra deve conter apenas caracteres numericos:");
				}
			} else {
				System.out.println("A palavra deve conter ate no maximo" + tamanho + " caracteres:");
			}
		} while (correto);
		return palavra;
	}

	public boolean verificaPossuiTamanhoCorreto(String texto, int tamanho, boolean ehCPF) {
		if (ehCPF) {
			return texto.length() == tamanho;
		} else {
			return texto.length() <= tamanho;
		}
	}

	public boolean verificaContemApenasNumeros(String texto) {
		return texto.matches("[0-9]+");
	}
}
