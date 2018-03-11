package arvore.binaria;

import java.util.Comparator;

public class NoArvoreBinaria<T> implements  Comparable<NoArvoreBinaria<T>>{

	private T info;
	private long frequencia;
	private NoArvoreBinaria<T> direita;
	private NoArvoreBinaria<T> esquerda;

	public NoArvoreBinaria(long frequencia) {
		this.frequencia = frequencia;
	}
	
	public NoArvoreBinaria(T info, long frequencia) {
		this.info = info;
		this.frequencia = frequencia;
	}

	public NoArvoreBinaria(T info, NoArvoreBinaria<T> esq, NoArvoreBinaria<T> dir) {
		this.info = info;
		this.direita = dir;
		this.esquerda = esq;
	}

	public T getInfo() {
		return info;
	}
	
	public long getFrequencia() {
		return frequencia;
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
	
	public String toString() {
		return "info: " + info + " - frequencia: " + frequencia;
	}

	@Override
	public int compareTo(NoArvoreBinaria<T> arg0) {
		if(getFrequencia() > arg0.getFrequencia()) {
			return 1;
		} else if (getFrequencia() < arg0.getFrequencia()) {
			return -1;
		}
		return 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (frequencia ^ (frequencia >>> 32));
		result = prime * result + ((info == null) ? 0 : info.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NoArvoreBinaria other = (NoArvoreBinaria) obj;
		if (frequencia != other.frequencia)
			return false;
		if (info == null) {
			if (other.info != null)
				return false;
		} else if (!info.equals(other.info))
			return false;
		return true;
	}
	
	

}
