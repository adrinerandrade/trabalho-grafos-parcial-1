package questao_2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class teste {

	public static void main(String[] args) {
		
		List<File> listaDeArquivos = new ArrayList();
		String caminhoArquivo;
		
		System.out.println("Insira o caminho dos arquivos que serão compactados: \n"
				+ "Obs1: Um por linha \n"
				+ "Obs2: Quando terminar apenas aperte Enter");
		do {
			Scanner scanner = new Scanner(System.in);
			caminhoArquivo = scanner.nextLine();	
			File arquivo = new File(caminhoArquivo);
			if(arquivo.exists()) {
				listaDeArquivos.add(arquivo);
			} else if (!caminhoArquivo.isEmpty()){
				System.out.println("Arquivo não encontrado! Tente novamente.");
			}
			
			caminhoArquivo = "";
		} while (!caminhoArquivo.isEmpty());
//		/home/luan/teste.txt
		
		
		String line;
		StringBuilder fullString = new StringBuilder();
		List objetosCompactados = new ArrayList<>();
		try {
			for(File arquivo: listaDeArquivos) {
				BufferedReader reader = new BufferedReader(new FileReader(arquivo));				
				while((line = reader.readLine()) != null) {
					if(fullString.length() > 0) fullString.append(System.lineSeparator());
					fullString.append(line);
				}   
				Compactador compactador = new Compactador(fullString.toString());
				compactador.compactar();
				
				ObjetoCompactado compactado = new ObjetoCompactado(compactador.getCompactados(), compactador.getArvore().toString());
				String caminhoArquivoCompactado = arquivo.getAbsoluteFile().toString().substring(0, arquivo.getAbsoluteFile().toString().lastIndexOf("."));
				
				ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(caminhoArquivoCompactado + ".compactado"));
				output.writeObject(compactado);
				objetosCompactados.add(caminhoArquivoCompactado);				
				
				output.close();
				reader.close();
			}			
//			Descompactador descompactador = new Descompactador(compactador.getCompactados(), compactador.getArvore());
//			System.out.println(descompactador.descompactar());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
