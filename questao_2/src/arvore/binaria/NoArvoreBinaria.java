package arvore.binaria;

import java.io.Serializable;

public class NoArvoreBinaria<T> implements  Comparable<NoArvoreBinaria<T>>, Serializable{

	private T info;
	private int peso;
	private NoArvoreBinaria<T> direita;
	private NoArvoreBinaria<T> esquerda;

	public NoArvoreBinaria(int peso) {
		this.peso = peso;
	}
	
	public NoArvoreBinaria(T info, int peso) {
		this.info = info;
		this.peso = peso;
	}

	public NoArvoreBinaria(T info, NoArvoreBinaria<T> esq, NoArvoreBinaria<T> dir) {
		this.info = info;
		this.direita = dir;
		this.esquerda = esq;
	}

	public T getInfo() {
		return info;
	}
	
	public int getPeso() {
		return peso;
	}

	public void setDireita(NoArvoreBinaria<T> direita) {
		this.direita = direita;
	}

	public void setEsquerda(NoArvoreBinaria<T> esquerda) {
		this.esquerda = esquerda;
	}

	public NoArvoreBinaria<T> getEsquerda() {
		return this.esquerda;
	}

	public NoArvoreBinaria<T> getDireita() {
		return this.direita;
	}
	
	@Override
	public String toString() {
		return arvorePre(this);
	}
	
	public String arvorePre(NoArvoreBinaria<T> no) {
		if (no == null) {
			return "<>";
		} else {
			T info = no.getInfo();
			Object valor_no = info == null ? "" : info;
			return String.format("<%s%s%s>", valor_no, this.arvorePre(no.getEsquerda()),
					this.arvorePre(no.getDireita()));
		}
	}

	@Override
	public int compareTo(NoArvoreBinaria<T> o2) {
		int diff  = this.peso - o2.peso;
		return diff == 0 ? -1 : diff;
	}
	
	public boolean ehFolha() {
		return this.direita == null && this.esquerda == null;
	}
	
}
