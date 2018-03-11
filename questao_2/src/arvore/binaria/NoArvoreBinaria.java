package arvore.binaria;

public class NoArvoreBinaria<T> {

	private T info;
	private NoArvoreBinaria<T> direita;
	private NoArvoreBinaria<T> esquerda;

	public NoArvoreBinaria(T info) {
		this.info = info;
	}

	public NoArvoreBinaria(T info, NoArvoreBinaria<T> esq, NoArvoreBinaria<T> dir) {
		this.info = info;
		this.direita = dir;
		this.esquerda = esq;
	}

	public T getInfo() {
		return info;
	}

	public NoArvoreBinaria<T> getEsquerda() {
		return this.esquerda;
	}

	public NoArvoreBinaria<T> getDireita() {
		return this.direita;
	}

}
