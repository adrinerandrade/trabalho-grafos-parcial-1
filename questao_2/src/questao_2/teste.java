package questao_2;

import arvore.binaria.ArvoreBinaria;
import arvore.binaria.NoArvoreBinaria;

public class teste {

	static ArvoreBinaria<Character> no;
	public static void main(String[] args) {
		
		Encriptador encriptador = new Encriptador("E aí cara, blz");
		StringBuilder valorDescriptografado = new StringBuilder();
		
		byte[] compactados = encriptador.compactar();
		NoArvoreBinaria<Character> raiz = encriptador.getArvore().getRaiz();
		
		no = new ArvoreBinaria<Character>(raiz); 
		
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < compactados.length; ++i) {
			String stringByte = String.format("%8s", Integer.toBinaryString(compactados[i] & 0xFF)).replace(' ', '0');
			sb.append(stringByte);
		}
		System.out.println(sb.toString());
		
		for (int i = 1; i < compactados.length; ++i) {
			String stringByte = Integer.toBinaryString(compactados[i] & 0xFF);
			stringByte.chars()
				.forEach(ch -> {
					if(ch == '0' && !no.getRaiz().ehFolha()) { // esquerda não folha
						no = new ArvoreBinaria<Character>(no.getRaiz().getEsquerda());
					} else if(ch == '1' && !no.getRaiz().ehFolha()) { //direita não folha
						no = new ArvoreBinaria<Character>(no.getRaiz().getDireita());
					} else {
						valorDescriptografado.append(no.getRaiz().getInfo());
						no = new ArvoreBinaria<Character>(raiz);
					}
				});
		}
		
		System.out.println(valorDescriptografado);
		
	}
	
	
	
}
