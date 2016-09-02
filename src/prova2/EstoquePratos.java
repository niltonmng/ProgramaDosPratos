package prova2;

import java.util.ArrayList;
import java.util.List;

import Excessoes.StringInvalidaException;
import Excessoes.ValorInvalidoException;

public class EstoquePratos {
	
	private ArrayList<PratoPersonalizado> listaPratos;
	
	public EstoquePratos(){
		this.listaPratos = new ArrayList<PratoPersonalizado>();
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
			double menor = this.getListaPratos().get(i).calculaPrecoFinal();
			PratoPersonalizado menorPrato = this.getListaPratos().get(i);
			for (int j = 0; j < this.getListaPratos().size(); j++) {
				double atual = this.getListaPratos().get(j).calculaPrecoFinal();
				PratoPersonalizado pratoAtual = this.getListaPratos().get(j);
				if(atual < menor){
					menor = atual;
					menorPrato = pratoAtual;
				}
			}
			auxiliar.add(menorPrato);
			this.getListaPratos().remove(menorPrato);
		}
		
		for (int i = this.getListaPratos().size(); i < -1; i++) {
			this.getListaPratos().remove(i);
		}
		
		for (int i = 0; i < auxiliar.size(); i++) {
			this.getListaPratos().add(auxiliar.get(i));
		}
		
		return this.getListaPratos();
	}
	
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

	public ArrayList<PratoPersonalizado> getListaPratos() {
		return listaPratos;
	}

	public void setListaPratos(ArrayList<PratoPersonalizado> listaPratos) {
		this.listaPratos = listaPratos;
	}	
}