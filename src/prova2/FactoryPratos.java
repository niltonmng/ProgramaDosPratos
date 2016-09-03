package prova2;

import Excessoes.StringInvalidaException;
import Excessoes.ValorInvalidoException;

public class FactoryPratos {	
	
	public PratoRetangular criaPratoRetangular(double precoBase, String personalizacao, int base, int altura) 
			throws StringInvalidaException, ValorInvalidoException{
		PratoRetangular pr = new PratoRetangular(precoBase, personalizacao, base, altura);
		return pr;
	}
	
	public PratoCircular criaPratoCircular(double precoBase, String personalizacao, int raio) 
			throws StringInvalidaException, ValorInvalidoException{
		PratoCircular pc = new PratoCircular(precoBase, personalizacao, raio);
		return pc;
	}
	
	public PratoTriangular criaPratoTriangular(double precoBase, String personalizacao, int base, int altura) 
			throws StringInvalidaException, ValorInvalidoException{
		PratoTriangular pt = new PratoTriangular(precoBase, personalizacao, base, altura);
		return pt;
	}

}
