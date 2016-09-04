package prova2;

import Excessoes.StringInvalidaException;
import Excessoes.ValorInvalidoException;

public class PratoRetangular extends PratoPersonalizado {
	
	private int base;
	private int altura;

	public PratoRetangular(double precoBase, String personalizacao, int base, int altura)
			throws StringInvalidaException, ValorInvalidoException {
		super(precoBase, personalizacao);
		if(base <= 0){
			throw new ValorInvalidoException("Dimensao nao pode ser negativa.");
		}
		if(altura <= 0){
			throw new ValorInvalidoException("Dimensao nao pode ser negativa.");
		}
		this.base = base;
		this.altura = altura;
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

	@Override
	public double calculaPrecoFinal() {
		return this.getPrecoBase() + (0.01 * this.calculaArea());
	}

	private double calculaArea() {
		return base * altura;
	}
	
	public String toString(){
		return super.toString() + " Formato retangular.";
	}

	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
}
