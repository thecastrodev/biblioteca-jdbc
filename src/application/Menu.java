package application;

import java.util.Scanner;

public class Menu {
	
	Scanner scanner = new Scanner(System.in);
	
	
	public void mostraMenu() {
		int escolha = 0;
		System.out.println("======Menu======= \n1 - Pesquisar Livros \n2 - Pesquisar Professores \n3 - Visualizar Reservas \n4 - Sair");
		
		// força a escolha de uma opção
				while (escolha < 1 || escolha > 3) {
					System.out.println("Escolha uma opção valida:");
					escolha = scanner.nextInt();
				}

				switch (escolha) {
				case 1:
					
					break;
				case 2:
					
					break;
				case 3:
					
					break;
				case 4:
					exit(1);
		
	}
	
}
