package prova2;

import Excessoes.StringInvalidaException;
import Excessoes.ValorInvalidoException;

public class PratoCircular extends PratoPersonalizado {
	
	private int raio;

	public PratoCircular(double precoBase, String personalizacao, int raio)
			throws StringInvalidaException, ValorInvalidoException {
		super(precoBase, personalizacao);
		if(raio <= 0){
			throw new ValorInvalidoException("Dimensao nao pode ser negativa.");
		}
		this.raio = raio;
	}
	
	public String toString(){
		return super.toString() + " Formato circular.";
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
		return 3.14 * raio * raio;
	}
}
