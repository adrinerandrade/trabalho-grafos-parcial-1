package questao_2;

public class teste {

	public static void main(String[] args) {
		Compactador compactador = new Compactador("E aí cara, blz?");
		compactador.compactar();
		Descompactador descompactador = new Descompactador(compactador.getCompactados(), compactador.getArvore());
		System.out.println(descompactador.descompactar());
	}

}
