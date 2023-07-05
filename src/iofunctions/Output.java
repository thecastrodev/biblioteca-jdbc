package iofunctions;

public class Output {
	public static void main() {

	}

// Funções de print
	public void clear() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	public void print(String text) {
		System.out.print(text);
	}

	public void line(String arg, int length) {
		for (int i = 0; i < length; i++) {
			System.out.print(arg);
		}
		System.out.print("\n");
	}

	public void title(String title) {
		line("=", title.length() + 10);
		System.out.println(center(title, title.length() + 10));
		line("=", title.length() + 10);
	}

	public void options(String[] options) {
		for (int i = 0; i < options.length; i++) {
			System.out.println(i + 1 + ". " + options[i]);
		}
	}

// Funções que retornam strings para regras de negócio
	public String titleToString(String title) {
		String output = "";
		output += line(title.length() + 10) + "\n";
		output += center(title, title.length() + 10) + "\n";
		output += line(title.length() + 10);
		return output;
	}

	public String center(String text, int length) {
		int spaces = (length - text.length()) / 2;
		String str = "";
		for (int i = 0; i < spaces; i++) {
			str += " ";
		}
		str += text;
		for (int i = 0; i < spaces; i++) {
			str += " ";
		}
		return str;
	}

// a line that just return the string for business rules
	public String line(int length) {
		String line = "";
		for (int i = 0; i < length; i++) {
			line += "=";
		}
		line += "\n";
		return line;
	}

	public String optionsToString(String[] options) {
		String str = "";
		for (int i = 0; i < options.length; i++) {
			str += i + 1 + ". " + options[i] + "\n";
		}
		return str;
	}

	public String minToHour(int minutes) {
		int hours = minutes / 60;
		int minutesLeft = minutes % 60;
		String str = "";
		if (hours > 0) {
			str += hours + "h ";
		}
		if (minutesLeft > 0) {
			str += minutesLeft + "m";
		}
		return str;
	}

// método exclusivo para o trabalho
	public String imprimirCadeiras(boolean[] cadeiras) {
		String str = "";
		for (int i = 0; i < cadeiras.length; i++) {
			if (cadeiras[i]) {
				str += "[ X ]";
			} else {
				str += "[ " + (i + 1) + " ]";
			}
			if ((i + 1) % 10 == 0) {
				str += "\n";
			}
		}
		return str;
	}
}