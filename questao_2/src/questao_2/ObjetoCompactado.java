package questao_2;

import java.io.Serializable;

import arvore.binaria.ArvoreBinaria;

public class ObjetoCompactado implements Serializable{
	private byte[] compactado;
	private String arvore;
	public ObjetoCompactado(byte[] compactado, String arvore) {
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
	public String getArvore() {
		return arvore;
	}
	public void setArvore(String arvore) {
		this.arvore = arvore;
	}
	
	
	
}
