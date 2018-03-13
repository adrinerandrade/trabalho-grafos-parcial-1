package questao_2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;

import arvore.binaria.ArvoreBinaria;
import arvore.binaria.NoArvoreBinaria;

public class Encriptador {

	private String value;
	private ArvoreBinaria<Character> arvore = new ArvoreBinaria<>();

	public Encriptador(String value) {
		this.value = value;
	}

	public byte[] compactar() {
		if (value == null || value == "") {
			return new byte[0];
		}
		Map<Character, Integer> frequencia = criarFrequencia(value);

		TreeSet<NoArvoreBinaria<Character>> nos = frequencia.entrySet().stream()
				.map(e -> new NoArvoreBinaria<>(e.getKey(), e.getValue()))
				.collect(Collectors.toCollection(TreeSet::new));

		this.arvore = new ArvoreBinaria<>(criarArvore(nos));

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < value.length(); ++i) {
			sb.append(obterBinario(value.charAt(i)));
		}
		System.out.println(sb.toString());
		
		List<String> bytesBinarios = new ArrayList<>();
		byte bitsFaltando = inserirBytesBinarios(sb.toString(), bytesBinarios);
		
		byte[] bytes = new byte[(int) bytesBinarios.size() + 1];
		bytes[0] = bitsFaltando;
		for (int i = 1; i < bytes.length; i++) {
			bytes[i] = (byte) Integer.parseInt(bytesBinarios.get(i - 1), 2);
		}
		
		return bytes;
	}

	private Map<Character, Integer> criarFrequencia(String value) {
		HashMap<Character, Integer> characteres = new HashMap<>();
		for (int i = 0; i < value.length(); ++i) {
			char character = value.charAt(i);
			if (characteres.get(character) == null) {
				characteres.put(character, 0);
			}
			characteres.put(character, characteres.get(character) + 1);
		}
		return characteres;
	}

	private NoArvoreBinaria<Character> criarArvore(TreeSet<NoArvoreBinaria<Character>> nos) {
		do {
			NoArvoreBinaria<Character> noEsquerda = nos.pollFirst();
			NoArvoreBinaria<Character> noDireita = nos.pollFirst();
			NoArvoreBinaria<Character> noArvore = new NoArvoreBinaria<Character>(
					getPeso(noEsquerda) + getPeso(noDireita));

			noArvore.setEsquerda(noEsquerda);
			noArvore.setDireita(noDireita);

			nos.add(noArvore);
		} while ((nos.size() > 1));

		return nos.pollFirst();
	}

	private int getPeso(NoArvoreBinaria<Character> no) {
		return no == null ? 0 : no.getPeso();
	}

	private String obterBinario(Character character) {
		StringBuilder caminho = new StringBuilder();
		percorrer(arvore.getRaiz(), character, null, caminho);
		return caminho.reverse().toString();
	}

	private boolean percorrer(NoArvoreBinaria<Character> no, Character character, Character caminhoAtual,
			StringBuilder caminho) {
		if (no == null) {
			return false;
		}
		boolean achou = character.equals(no.getInfo()) || percorrer(no.getEsquerda(), character, '0', caminho)
				|| percorrer(no.getDireita(), character, '1', caminho);

		if (achou && caminhoAtual != null) {
			caminho.append(caminhoAtual);
		}
		return achou;
	}

	private byte inserirBytesBinarios(String value, List<String> bytesBinarios) {
		if (bytesBinarios == null) {
			bytesBinarios = new LinkedList<>();
		}
		StringBuilder byteBinario = new StringBuilder();
		for (int i = 0; i < value.length(); ++i) {
			byteBinario.append(value.charAt(i));
			if (i % 8 == 7) {
				bytesBinarios.add(byteBinario.toString());
				byteBinario = new StringBuilder();
			}
		}
		if (byteBinario.length() > 0) {
			byte bitsFaltando = (byte) (Byte.SIZE - byteBinario.length());
			for (byte i = 0; i < bitsFaltando; ++i) {
				byteBinario.append('0');
			}
			bytesBinarios.add(byteBinario.toString());
			return bitsFaltando;
		}
		return 0;
	}

	public ArvoreBinaria<Character> getArvore() {
		return arvore;
	}
	
	

}
