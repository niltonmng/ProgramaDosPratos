package prova2;

import java.util.ArrayList;
import java.util.List;

import Excessoes.StringInvalidaException;
import Excessoes.ValorInvalidoException;

public class EstoquePratos {
	
	private ArrayList<PratoPersonalizado> listaPratos;
	private FactoryPratos fp;
	
	public EstoquePratos(){
		this.listaPratos = new ArrayList<PratoPersonalizado>();
		fp = new FactoryPratos();
	}

	public boolean adicionaPrato(PratoPersonalizado prato){
		return this.getListaPratos().add(prato);
	}
	
	public boolean removePrato(PratoPersonalizado prato){
		return this.getListaPratos().remove(prato);
	}
	
	public int getQtdPratos(){
		return this.getListaPratos().size();
	}
	
	public boolean consultaPratoComPersonalizacao(String personalizacao){
		for (int i = 0; i < this.getListaPratos().size(); i++) {
			PratoPersonalizado p = this.getListaPratos().get(i);
			if(p.getPersonalizacao().equalsIgnoreCase(personalizacao)){
				return true;
			}
		}
		return false;
	}
	
	public PratoPersonalizado getPratoComPersonalizacao(String personalizacao){
		for (int i = 0; i < this.getListaPratos().size(); i++) {
			PratoPersonalizado p = this.getListaPratos().get(i);
			if(p.getPersonalizacao().equalsIgnoreCase(personalizacao)){
				return p;
			}
		}
		return null;
	}
	
	public double calculaLucroTotal(){
		double precoTotal = 0.0;
		for (int i = 0; i < this.getListaPratos().size(); i++) {
			double precoPrato = this.getListaPratos().get(i).calculaPrecoFinal();
			precoTotal += precoPrato;
		}
		return precoTotal;
	}
	
	public List<PratoPersonalizado> getPratosOrdenadosPorPreco(){
		List<PratoPersonalizado> auxiliar = new ArrayList<PratoPersonalizado>();
		
		for (int i = 0; i < this.getListaPratos().size(); i++) {			
			PratoPersonalizado menorPrato = this.getListaPratos().get(i);
			for (int j = 0; j < this.getListaPratos().size(); j++) {
				PratoPersonalizado pratoAtual = this.getListaPratos().get(j);
				if(pratoAtual.compareTo(menorPrato) > 0){
					menorPrato = pratoAtual;
				}
				break;
			}
			auxiliar.add(menorPrato);
			this.getListaPratos().remove(menorPrato);
		}
		
		for (int i = 0; i < auxiliar.size(); i++) {
			this.getListaPratos().add(auxiliar.get(i));
		}
		
		return this.getListaPratos();
	}
	
	public PratoRetangular criaPratoRetangular(double precoBase, String personalizacao, int base, int altura) 
			throws StringInvalidaException, ValorInvalidoException{
		return fp.criaPratoRetangular(precoBase, personalizacao, base, altura);
	}
	
	public PratoCircular criaPratoCircular(double precoBase, String personalizacao, int raio) 
			throws StringInvalidaException, ValorInvalidoException{
		return fp.criaPratoCircular(precoBase, personalizacao, raio);
	}
	
	public PratoTriangular criaPratoTriangular(double precoBase, String personalizacao, int base, int altura) 
			throws StringInvalidaException, ValorInvalidoException{
		return fp.criaPratoTriangular(precoBase, personalizacao, base, altura);
	}
	

	public ArrayList<PratoPersonalizado> getListaPratos() {
		return listaPratos;
	}

	public void setListaPratos(ArrayList<PratoPersonalizado> listaPratos) {
		this.listaPratos = listaPratos;
	}	
}