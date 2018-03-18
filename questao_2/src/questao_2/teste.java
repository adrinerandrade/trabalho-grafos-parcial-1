package questao_2;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import arvore.binaria.ArvoreBinaria;

public class teste {

	public static void main(String[] args) {
		
		List<File> listaDeArquivos = new ArrayList();
		String caminhoArquivo;
		String opcaoSelecionada;
		final String extencao = ".compactado";
		final String extencaoArvore = ".arvore";
		String pathArquivo;
		
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
					if(arquivo.exists()) {
						listaDeArquivos.add(arquivo);
					} else if (!caminhoArquivo.isEmpty()){
						System.out.println("Arquivo não encontrado! Tente novamente.");
					}
					
					caminhoArquivo = "";
				} while (!caminhoArquivo.isEmpty());
//				/home/luan/teste.txt
				
				String line;
				StringBuilder fullString = new StringBuilder();
				try {
					for(File arquivo: listaDeArquivos) {
						BufferedReader reader = new BufferedReader(new FileReader(arquivo));				
						while((line = reader.readLine()) != null) {
							if(fullString.length() > 0) fullString.append(System.lineSeparator());
							fullString.append(line);
						}   
						Compactador compactador = new Compactador(fullString.toString());
						compactador.compactar();
						
						String caminhoArquivoCompactado = arquivo.getAbsoluteFile().toString().substring(0, arquivo.getAbsoluteFile().toString().lastIndexOf("."));
						
						ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(caminhoArquivoCompactado + extencao));
						output.writeObject(compactador.getCompactados()); //Salvando apenas o array de bytes				
						
						System.out.println(arquivo.length());
						System.out.println(new File(caminhoArquivoCompactado + extencao).length());
						
						output.close();
						reader.close();				
						
						ObjectOutputStream outputArvore = new ObjectOutputStream(new FileOutputStream(caminhoArquivoCompactado + extencaoArvore));
						outputArvore.writeObject(compactador.getArvore()); //Salvando apenas o array de bytes
						outputArvore.close();
					}			
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
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
					if(arquivo.exists()) {
						listaDeArquivos.add(arquivo);
					} else if (!caminhoArquivo.isEmpty()){
						System.out.println("Arquivo não encontrado! Tente novamente.");
					}
					
					caminhoArquivo = "";
				} while (!caminhoArquivo.isEmpty());
				
				
				for(File arquivo: listaDeArquivos) {
					try {
//						/home/luan/teste.compactado
						
						ObjectInputStream streamCompactado = new ObjectInputStream(new FileInputStream(arquivo));
						byte[] objetoCompactado = (byte[]) streamCompactado.readObject();
						
						String caminhoArquivoCompactado = arquivo.getAbsoluteFile().toString().substring(0, arquivo.getAbsoluteFile().toString().lastIndexOf("."));
						ObjectInputStream streamArvore = new ObjectInputStream(new FileInputStream(caminhoArquivoCompactado + extencaoArvore));
						ArvoreBinaria<Character> arvore = (ArvoreBinaria<Character>) streamArvore.readObject();
						
						Descompactador descompactador = new Descompactador(objetoCompactado, arvore);
						
						PrintWriter writer = new PrintWriter(new FileOutputStream(caminhoArquivoCompactado + "descompactado.txt"));
						writer.print(descompactador.descompactar());
						writer.close();
						streamArvore.close();
						
//						System.out.println(descompactador.descompactar());
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
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
