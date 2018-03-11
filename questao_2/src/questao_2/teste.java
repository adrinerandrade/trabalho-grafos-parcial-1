package questao_2;

import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

import arvore.binaria.NoArvoreBinaria;

public class teste {

	public static void main(String[] args) {
		TreeSet<NoArvoreBinaria<Character>> arvore = new TreeSet<NoArvoreBinaria<Character>>();
		
		String iputString = "oi, meu nome é Luan";
		Set<Character> unique = new HashSet<Character>();
		
		for (int i = 0; i < iputString.length(); i++) {
	        unique.add(iputString.charAt(i));
		}
		
		for(char s: unique) {
			long val = iputString.chars().filter(ch -> ch == s).count();			
			NoArvoreBinaria<Character> no =new NoArvoreBinaria<Character>(s, val); 
			arvore.add(no);
		}
		
		while(arvore.size() != 1) {
			NoArvoreBinaria<Character> noEsquerda = arvore.pollFirst();
			NoArvoreBinaria<Character> noDireita = arvore.pollFirst();
			NoArvoreBinaria<Character> noArvore = new NoArvoreBinaria<Character>(noDireita.getFrequencia() + noEsquerda.getFrequencia());
			
			noArvore.setEsquerda(noEsquerda);
			noArvore.setDireita(noDireita);
			
			arvore.add(noArvore);			
		}
		
		escreveArvore(arvore.pollFirst());
	}
	
	public static void escreveArvore(NoArvoreBinaria arvore) {
		if(arvore.getDireita() != null) {
			escreveArvore(arvore.getDireita());
		}
		if(arvore.getEsquerda() != null) {
			escreveArvore(arvore.getEsquerda());
		}
		
		System.out.println(arvore);
	}
}

