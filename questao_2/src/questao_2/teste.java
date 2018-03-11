package questao_2;

public class teste {

	public static void main(String[] args) {
		
		byte[] compactados = new Encriptador("E aí cara, blz").compactar();
		for (int i = 0; i < compactados.length; ++i) {
			System.out.println(compactados[i]);
		}
		
	}
}
