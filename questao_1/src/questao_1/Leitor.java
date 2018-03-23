package questao_1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;

public class Leitor {

	private static Dicionario dicionario = new Dicionario();

	public static void main(String[] args) {
		System.out.println("Digite o caminho para o arquivo selecionado.");
		Scanner keyboard = new Scanner(System.in);
		String caminho = "";  
		File arquivo;
		do {
			caminho = keyboard.nextLine();			
			arquivo = new File(caminho);
			
			if(!arquivo.exists()) {
				System.out.println("Arquivo não encontrado. Insira novamente.");
				caminho = "";
			}
			
		} while (caminho.isEmpty());
		
		String line;
		StringBuilder content = new StringBuilder();
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(arquivo));
			while((line = reader.readLine()) != null) {
				if(content.length() > 0) content.append(System.lineSeparator());
				content.append(line);
			}   				
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}				

		dicionario.reconhecerPalavras(content.toString());

		Set<String> palavrasEncontradas = dicionario.getPalavras();
		for (String palavra : palavrasEncontradas) {
			System.out.println(palavra);
		}
	}

}
