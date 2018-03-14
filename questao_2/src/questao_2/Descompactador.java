package questao_2;

import arvore.binaria.ArvoreBinaria;
import arvore.binaria.NoArvoreBinaria;

public class Descompactador {

	private int index;
	private byte[] compactados;
	private ArvoreBinaria<Character> arvore;
	
	public Descompactador(byte[] compactados, ArvoreBinaria<Character> arvore) {
		this.compactados = compactados;
		this.arvore = arvore;
	}

	public String descompactar() {
		if (compactados.length < 1) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < compactados.length; ++i) {
			String stringByte = String.format("%8s", Integer.toBinaryString(compactados[i] & 0xFF)).replace(' ', '0');
			sb.append(stringByte);
		}
		String descompactada = sb.toString().substring(0, sb.length() - compactados[0]);

		StringBuilder valorFinal = new StringBuilder();
		index = 0;
		while (index < descompactada.length()) {
			valorFinal.append(getCharacter(arvore.getRaiz(), descompactada));
		}
		return valorFinal.toString();
	}

	private Character getCharacter(NoArvoreBinaria<Character> no, String descompactada) {
		if (no.ehFolha()) {
			return no.getInfo();
		} else {
			char sentido = descompactada.charAt(index++);
			if (sentido == '0') {
				return getCharacter(no.getEsquerda(), descompactada);
			}
			return getCharacter(no.getDireita(), descompactada);
		}
	}
	
}
