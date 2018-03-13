package questao_2;

import arvore.binaria.NoArvoreBinaria;

public class teste {

	private static int index;

	public static void main(String[] args) {
		Compactador compactador = new Compactador("E aí cara, blz?");

		byte[] compactados = compactador.compactar();

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < compactados.length; ++i) {
			String stringByte = String.format("%8s", Integer.toBinaryString(compactados[i] & 0xFF)).replace(' ', '0');
			sb.append(stringByte);
		}
		String descompactada = sb.toString().substring(0, sb.length() - compactados[0]);

		StringBuilder valorFinal = new StringBuilder();
		while (index < descompactada.length()) {
			valorFinal.append(getCharacter(compactador.getArvore().getRaiz(), descompactada));
		}
		System.out.println(valorFinal);
	}

	private static Character getCharacter(NoArvoreBinaria<Character> no, String descompactada) {
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
