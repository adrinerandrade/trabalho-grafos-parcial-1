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
		
		for (int i = 1; i < compactados.length; ++i) {
			System.out.println(Integer.toBinaryString(compactados[i]));
			
//			Integer.toBinaryString((int)compactados[i]).chars()
//				.forEach(ch -> {
//					if(ch == '0' && !no.getRaiz().eFolha()) { // esquerda não folha
//						no = new ArvoreBinaria<Character>(no.getRaiz().getEsquerda());
//					} else if(ch == '1' && !no.getRaiz().eFolha()) { //direita não folha
//						no = new ArvoreBinaria<Character>(no.getRaiz().getDireita());
//					} else {
//						valorDescriptografado.append(no.getRaiz().getInfo());
//						no = new ArvoreBinaria<Character>(raiz);
//					}
//				});
		}
		
		System.out.println(valorDescriptografado);
		
		
		
	}
}
