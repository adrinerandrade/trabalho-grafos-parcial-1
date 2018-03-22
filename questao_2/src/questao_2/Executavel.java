package questao_2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import arvore.binaria.ArvoreBinaria;

public class Executavel {

	public static void main(String[] args) {
		
		List<File> listaDeArquivos = new ArrayList<File>();
		String caminhoArquivo;
		String opcaoSelecionada;
		final String extencao = ".compactado";
		final String extencaoArvore = ".arvore";
		
		System.out.println("Digite a opção: \n"
				+ "1 - Compactar \n"
				+ "2 - Decompactar");
		
		do {
			Scanner scanner = new Scanner(System.in);
			opcaoSelecionada = scanner.nextLine();	
			
			if(opcaoSelecionada.equals("1")) {		
				System.out.println("Insira o caminho dos arquivos que serão compactados: \n"
						+ "Obs1: Um por linha \n"
						+ "Obs2: Quando terminar apenas aperte Enter");
				do {
					caminhoArquivo = scanner.nextLine();	
					File arquivo = new File(caminhoArquivo);
					if(arquivo.exists() && !caminhoArquivo.isEmpty()) {
						listaDeArquivos.add(arquivo);
						System.out.println("Arquivo adicionado para processamento. Se desejar adicione outro ou aperte enter para continuar.");
					} else if (!arquivo.exists() && !caminhoArquivo.isEmpty()){
						System.out.println("Arquivo não encontrado! Tente novamente.");
					}
					
				} while (!caminhoArquivo.isEmpty());
				
				try {
					for(File arquivo: listaDeArquivos) {
						
						long tempoInicio = System.nanoTime();        
						String line;
						StringBuilder fullString = new StringBuilder();
						BufferedReader reader = new BufferedReader(new FileReader(arquivo));				
						while((line = reader.readLine()) != null) {
							if(fullString.length() > 0) fullString.append(System.lineSeparator());
							fullString.append(line);
						}   
						Compactador compactador = new Compactador(fullString.toString());
						compactador.compactar();
						
						String caminhoArquivoCompactado = arquivo.getAbsoluteFile().toString().substring(0, arquivo.getAbsoluteFile().toString().lastIndexOf("."));
						
						FileOutputStream stream = new FileOutputStream(caminhoArquivoCompactado + extencao);
						stream.write(compactador.getCompactados());
						
						long tamOriginal = arquivo.length();
						long tamFinal = new File(caminhoArquivoCompactado + extencao).length();
					    double tempoFinal = (System.nanoTime() - tempoInicio) /  1000000;
						
						System.out.println("Arquivo: " + arquivo.getName() + 
								" - Tamanho original: " + tamOriginal + " bytes" +
								" - Tamanho após compressão: " + tamFinal + " bytes" +
								" - Tempo de execução: " + tempoFinal + "ms");
						
						stream.close();
						reader.close();		
						
						ObjectOutputStream outputArvore = new ObjectOutputStream(new FileOutputStream(caminhoArquivoCompactado + extencaoArvore));
						outputArvore.writeObject(compactador.getArvore());
						outputArvore.close();
					}			
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				
			} else if (opcaoSelecionada.equals("2")) {
				
				System.out.println("Insira o caminho dos arquivos que serão descompactados: \n"
						+ "Obs1: Um por linha \n"
						+ "Obs2: Quando terminar apenas aperte Enter \n"
						+ "Obs3: É necessário que exista o arquivo .arvore correspondente ao arquivo para a descompactação");
				do {
					caminhoArquivo = scanner.nextLine();	
					File arquivo = new File(caminhoArquivo);
					
					if(arquivo.exists() && !caminhoArquivo.isEmpty()) {						
						String caminhoArquivoCompactado = arquivo.getAbsoluteFile().toString().substring(0, arquivo.getAbsoluteFile().toString().lastIndexOf("."));
						File arquivoArvore = new File(caminhoArquivoCompactado + extencaoArvore);
						if(arquivoArvore.exists()) {
							listaDeArquivos.add(arquivo);
							System.out.println("Arquivo adicionado para processamento. Se desejar adicione outro ou aperte enter para continuar.");
						} else {
							System.out.println("Arquivo .arvore não existe para o arquivo correspondente."
									+ " Insira o caminho de um arquivo compactado por esse programa.");
						}
						
					} else if (!arquivo.exists() && !caminhoArquivo.isEmpty()) {
						System.out.println("Arquivo não encontrado! Tente novamente.");
					} 		
				} while (!caminhoArquivo.isEmpty());
				
				
				for(File arquivo: listaDeArquivos) {
					try {
						FileInputStream streamCompactado = new FileInputStream(arquivo);
						byte[] objetoCompactado = (byte[]) streamCompactado.readAllBytes();
						
						String caminhoArquivoCompactado = arquivo.getAbsoluteFile().toString().substring(0, arquivo.getAbsoluteFile().toString().lastIndexOf("."));
						ObjectInputStream streamArvore = new ObjectInputStream(new FileInputStream(caminhoArquivoCompactado + extencaoArvore));
						ArvoreBinaria<Character> arvore = (ArvoreBinaria<Character>) streamArvore.readObject();
						
						Descompactador descompactador = new Descompactador(objetoCompactado, arvore);
						
						PrintWriter writer = new PrintWriter(new FileOutputStream(caminhoArquivoCompactado + ".descompactado.txt"));
						writer.print(descompactador.descompactar());
						writer.close();
						streamArvore.close();
						streamCompactado.close();
						

						long tamCompactado = arquivo.length();
						long tamDescompactado = new File(caminhoArquivoCompactado + ".descompactado.txt").length();
						
						
						System.out.println("Arquivo: " + arquivo.getName() + 
								" - Tamanho compactado: " + tamCompactado + " bytes" +
								" - Tamanho após descompressão: " + tamDescompactado + " bytes");
						
					} catch (IOException e) {
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				}				
			} else {
				System.out.println("Opção invalida! Selecione uma das opções acima.");
				opcaoSelecionada = "";
			}
		} while (opcaoSelecionada.isEmpty());
		
	}

}
