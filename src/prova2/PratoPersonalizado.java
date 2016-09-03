package prova2;

import Excessoes.StringInvalidaException;
import Excessoes.ValorInvalidoException;

public abstract class PratoPersonalizado implements Comparable{
	
	private double precoBase;
	private String personalizacao;
	
	public PratoPersonalizado(double precoBase, String personalizacao) throws StringInvalidaException, ValorInvalidoException{
		if(precoBase < 0){
			throw new StringInvalidaException("Preco base nao pode ser negativo.");
		}
		if(personalizacao.equalsIgnoreCase("foto") == false && personalizacao.equalsIgnoreCase("pintura") == false && personalizacao.equalsIgnoreCase("estampa") == false){
			throw new StringInvalidaException("Nao criamos pratos com " + personalizacao + ". Por favor, escolha outra opcao.");
		}
		if(personalizacao == null || personalizacao.trim().isEmpty()){
			throw new ValorInvalidoException("Personalizacao nao pode ser nula ou vazia.");
		}		
		this.precoBase = precoBase;
		this.personalizacao = personalizacao;
	}
	
	public abstract double calculaPrecoFinal();

	public double getPrecoBase() {
		return precoBase;
	}

	public void setPrecoBase(double precoBase) {
		this.precoBase = precoBase;
	}

	public String getPersonalizacao() {
		return personalizacao;
	}

	public void setPersonalizacao(String personalizacao) {
		this.personalizacao = personalizacao;
	}
	
	public String toString(){
		String personalizacao = "";
		personalizacao += this.getPersonalizacao().toUpperCase().charAt(0);
		for (int i = 1; i < this.getPersonalizacao().length(); i++) {
			personalizacao += this.getPersonalizacao().toLowerCase().charAt(i);
		}
		return "Prato com " + personalizacao + " que custa R$ "+ this.getPrecoBase() + ".";
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((personalizacao == null) ? 0 : personalizacao.hashCode());
		long temp;
		temp = Double.doubleToLongBits(precoBase);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	public boolean equals(Object obj) {
		if(obj instanceof PratoPersonalizado){
			PratoPersonalizado p = (PratoPersonalizado)obj;
			if(this.getPersonalizacao().equalsIgnoreCase(p.getPersonalizacao())){
				return true;
			}
		}
		return false;
	}

	public int compareTo(PratoPersonalizado prato) {
		if(this.getPrecoBase() > prato.getPrecoBase()){
			return 1;
		}else if(this.getPrecoBase() < prato.getPrecoBase()){
			return -1;
		}else{
			return 0;			
		}
	}
}