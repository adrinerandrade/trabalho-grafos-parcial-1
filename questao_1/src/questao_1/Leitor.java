package questao_1;

import java.util.Scanner;
import java.util.Set;

public class Leitor {

	private static Dicionario dicionario = new Dicionario();

	public static void main(String[] args) {
		System.out.println("Insira um conteúdo, e digite \"##fim\" em uma linha para sinalizar o fim da operação.");
		Scanner keyboard = new Scanner(System.in);
		StringBuilder content = new StringBuilder();
		String line;
		while (!"##fim".equals((line = keyboard.nextLine()))) {
			content.append(line);
			content.append("\n");
		}
		keyboard.close();

		dicionario.reconhecerPalavras(content.toString());

		Set<String> palavrasEncontradas = dicionario.getPalavras();
		for (String palavra : palavrasEncontradas) {
			System.out.println(palavra);
		}
	}

}
