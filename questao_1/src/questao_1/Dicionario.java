package questao_1;

import java.text.Normalizer;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

public class Dicionario {

	private final Queue<Character> fila = new LinkedList<>();
	private final Set<String> palavras = new HashSet<>();
	
	public void reconhecerPalavras(String content) {
		if (content == null || content.trim() == "") {
			return;
		}
		
		for (int i = 0; i < content.length(); ++i) {
			char character = content.charAt(i);
			if (Character.isLetter(character) || '-' == character) {
				fila.offer(character);
			} else if (!fila.isEmpty()) {
				adicionarPalavra();
			}
		}
		
		if (!fila.isEmpty()) {
			adicionarPalavra();
		}
	}
	
	public Set<String> getPalavras() {
		return palavras.stream().
				sorted((p1, p2) -> {
					p1 = Normalizer.normalize(p1, Normalizer.Form.NFD);
			        p2 = Normalizer.normalize(p2, Normalizer.Form.NFD);
			        return p1.compareTo(p2);
				}).
				collect(Collectors.toCollection(LinkedHashSet::new));
	}
	
	private void adicionarPalavra() {
		StringBuilder sb = new StringBuilder();
		while (!fila.isEmpty()) {
			Character character = fila.remove();
			sb.append(Character.toLowerCase(character));
		}
		palavras.add(sb.toString());
	}

}
