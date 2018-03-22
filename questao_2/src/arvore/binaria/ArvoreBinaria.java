package arvore.binaria;

import java.io.Serializable;

public class ArvoreBinaria<T> implements Serializable{

	private static final long serialVersionUID = -2015383912828995547L;
	private NoArvoreBinaria<T> raiz;

	public ArvoreBinaria() {
	}

	public ArvoreBinaria(NoArvoreBinaria<T> raiz) {
		this.raiz = raiz;
	}

	public void setRaiz(NoArvoreBinaria<T> raiz) {
		this.raiz = raiz;
	}

	public NoArvoreBinaria<T> getRaiz() {
		return raiz;
	}

	public boolean estavaVazia() {
		return raiz != null;
	}

	public boolean pertence(T info) {
		return this.pertence(raiz, info);
	}

	public boolean pertence(NoArvoreBinaria<T> no, T info) {
		if (no == null) {
			return false;
		} else
			return (no.getInfo().equals(info) || pertence(no.getEsquerda(), info) || pertence(no.getDireita(), info));
	}

	@Override
	public String toString() {
		return raiz == null ? "<>" : raiz.toString();
	}

}
