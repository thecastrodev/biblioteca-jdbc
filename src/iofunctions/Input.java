package iofunctions;

import java.util.Scanner;

public class Input {
	public Scanner stdin = new Scanner(System.in);

	public String readString(String prompt) {
		System.out.print(prompt);
		return stdin.nextLine();
	}

	public int readInt(String prompt) {
		boolean flag = false;
		int num = 0;
		while (!flag) {
			try {
				System.out.print(prompt + " ");
				num = Integer.parseInt(stdin.nextLine());
				flag = true;
			} catch (NumberFormatException ex) {
				System.out.println("Please enter an integer.");
			}
		}
		return num;
	}

	public int readInt(String prompt, int min, int max) {
		boolean flag = false;
		int num = 0;
		while (!flag) {
			try {
				System.out.print(prompt + " ");
				num = Integer.parseInt(stdin.nextLine());
				if (num >= min && num <= max) {
					flag = true;
				} else {
					System.out.println("Please enter a number between " + min + " and " + max + ".");
				}
			} catch (NumberFormatException ex) {
				System.out.println("Please enter an integer.");
			}
		}
		return num;
	}

	public int readInt(String prompt, int[] values) {
		boolean flag = false;
		int num = 0;
		while (!flag) {
			try {
				System.out.print(prompt + " ");
				num = Integer.parseInt(stdin.nextLine());
// catching the valid input
				for (int i = 0; i < values.length; i++) {
					if (num == values[i]) {
						flag = true;
					}
				}
				if (!flag) {
					System.out.println("Please enter a valid number.");
				}
			} catch (NumberFormatException ex) {
				System.out.println("Please enter an integer.");
			}
		}
		return num;
	}

	public double readDouble(String prompt) {
		boolean flag = false;
		double num = 0;
		while (!flag) {
			try {
				System.out.print(prompt + " ");
				num = Double.parseDouble(stdin.nextLine());
				flag = true;
			} catch (NumberFormatException ex) {
				System.out.println("Please enter a double.");
			}
		}
		return num;
	}

	public double readDouble(String prompt, double min, double max) {
		boolean flag = false;
		double num = 0;
		while (!flag) {
			try {
				System.out.print(prompt + " ");
				num = Double.parseDouble(stdin.nextLine());
				if (num >= min && num <= max) {
					flag = true;
				} else {
					System.out.println("Please enter a number between " + min + " and " + max + ".");
				}
			} catch (NumberFormatException ex) {
				System.out.println("Please enter a double.");
			}
		}
		return num;
	}

	public boolean yesNo(String prompt) {
		boolean flag = false;
		String answer = "";
		while (!flag) {
			answer = readString(prompt);
			if (answer.equals("y") || answer.equals("Y"))
				return true;

			else if (answer.equals("n") || answer.equals("N")) {
				return false;

			} else {
				System.out.println("Please enter 'y' or 'n'.");
			}
		}
		return answer.equals("y");
	}

	public void pause() {
		System.out.println("\nPress enter to continue.");
		stdin.nextLine();
	}

	public void pause(String prompt) {
		System.out.println(prompt);
		stdin.nextLine();
	}

	public void close() {
		stdin.close();
	}

	public int menu(String title, String[] options) {
		Output output = new Output();
		int choice = 0;
		output.clear();
		output.title(title);
		output.options(options);
		choice = readInt("Please select an option: ", 1, options.length);
		output.clear();
		return choice;
	}

}