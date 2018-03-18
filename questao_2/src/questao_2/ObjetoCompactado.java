package questao_2;

import java.io.Serializable;

import arvore.binaria.ArvoreBinaria;

public class ObjetoCompactado implements Serializable{
	private byte[] compactado;
	private ArvoreBinaria<Character> arvore;
	
	
	
	public ObjetoCompactado(byte[] compactado, ArvoreBinaria<Character> arvore) {
		super();
		this.compactado = compactado;
		this.arvore = arvore;
	}
	public byte[] getCompactado() {
		return compactado;
	}
	public void setCompactado(byte[] compactado) {
		this.compactado = compactado;
	}
	public ArvoreBinaria<Character> getArvore() {
		return arvore;
	}
	public void setArvore(ArvoreBinaria<Character> arvore) {
		this.arvore = arvore;
	}
	
	
	
	
}
