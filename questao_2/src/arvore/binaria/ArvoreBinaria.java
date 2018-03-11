package arvore.binaria;

public class ArvoreBinaria<T> {

	private NoArvoreBinaria<T> raiz;

	public ArvoreBinaria() {
		raiz = null;
	}

	public void setRaiz(NoArvoreBinaria<T> raiz) {
		this.raiz = raiz;
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
		return arvorePre(raiz);
	}

	public String arvorePre(NoArvoreBinaria<T> no) {
		if (no == null) {
			return "<>";
		} else {
			String strLocal = "<" + no.getInfo() + this.arvorePre(no.getEsquerda()) + this.arvorePre(no.getDireita())
					+ ">";
			return strLocal;
		}
	}

}
