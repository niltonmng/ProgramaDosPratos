package testes;

import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import prova2.EstoquePratos;
import prova2.FactoryPratos;
import prova2.PratoPersonalizado;

public class TestEstoquePrato {

	private EstoquePratos estoque;
	
	@Before
	public void setUp(){
		estoque = new EstoquePratos();
	}
	
	@Test
	public void testPasso1() throws Exception{
		//base = 30, altura = 25
		PratoPersonalizado pratoEstampado = estoque.criaPratoRetangular(6.0, "Estampa", 30, 25);
		PratoPersonalizado pratoComFoto = estoque.criaPratoRetangular(4.5, "Foto", 25, 30);
		PratoPersonalizado pratoPintado = estoque.criaPratoRetangular(8.20, "Pintura", 30, 30);
		PratoPersonalizado pratoComPintura = estoque.criaPratoRetangular(6.10, "Pintura", 15, 40);
		
		Assert.assertEquals(6.0, pratoEstampado.getPrecoBase(), 0.01);
		Assert.assertEquals(4.5, pratoComFoto.getPrecoBase(), 0.01);
		Assert.assertEquals(8.20, pratoPintado.getPrecoBase(), 0.01);
		
		Assert.assertTrue(pratoComPintura.equals(pratoPintado));
		Assert.assertFalse(pratoComPintura.equals(pratoComFoto));
		
		//extra: adicione testes para o get e set de personalizacao;
	}
	
	@Test
	public void testPasso2() throws Exception{
		//base = 30, altura = 25
		PratoPersonalizado pratoCircular = estoque.criaPratoCircular(6.0, "Estampa", 30);
		PratoPersonalizado pratoRetangular = estoque.criaPratoRetangular(4.5, "Foto", 25, 30);
		PratoPersonalizado pratoTriangular = estoque.criaPratoTriangular(8.20, "Estampa", 30, 30);
		
		Assert.assertEquals(6.0, pratoCircular.getPrecoBase(), 0.01);
		Assert.assertEquals(4.5, pratoRetangular.getPrecoBase(), 0.01);
		Assert.assertEquals(8.20, pratoTriangular.getPrecoBase(), 0.01);
		
		String circular = "Prato com Estampa que custa R$ 6.0. Formato circular.";
		String retangular = "Prato com Foto que custa R$ 4.5. Formato retangular.";
		String triangular = "Prato com Estampa que custa R$ 8.2. Formato triangular.";
		
		Assert.assertEquals(circular, pratoCircular.toString());
		Assert.assertEquals(retangular, pratoRetangular.toString());
		Assert.assertEquals(triangular, pratoTriangular.toString());
		
		Assert.assertTrue(pratoCircular.equals(pratoTriangular));
		Assert.assertFalse(pratoCircular.equals(pratoRetangular));		
	}
	
	@Test
	public void testPasso3() throws Exception{
		//raio = 30 cm.
		PratoPersonalizado pratoCircular = estoque.criaPratoCircular(6.0, "Estampa", 30);
		//base = 30, altura = 25 (em cm).
		PratoPersonalizado pratoRetangular = estoque.criaPratoRetangular(4.5, "Foto", 25, 30);
		//base = 30, altura = 25 (em cm).
		PratoPersonalizado pratoTriangular = estoque.criaPratoTriangular(8.20, "Estampa", 30, 30);
		
		estoque.adicionaPrato(pratoTriangular);
		estoque.adicionaPrato(pratoRetangular);
		estoque.adicionaPrato(pratoCircular);
		
		Assert.assertEquals(34.26, pratoCircular.calculaPrecoFinal(), 0.01);
		Assert.assertEquals(12.0, pratoRetangular.calculaPrecoFinal(), 0.01);
		Assert.assertEquals(12.7, pratoTriangular.calculaPrecoFinal(), 0.01);
		Assert.assertEquals(58.96, estoque.calculaLucroTotal(), 0.01);
	}
	
	@Test
	public void testPasso4() throws Exception{
		estoque = new EstoquePratos();
		PratoPersonalizado pratoCircular = estoque.criaPratoCircular(6.0, "Estampa", 30);
		PratoPersonalizado pratoRetangular = estoque.criaPratoRetangular(4.5, "Foto", 25, 30);
		PratoPersonalizado pratoTriangular = estoque.criaPratoTriangular(8.20, "Estampa", 30, 30);
		
		Assert.assertTrue(estoque.adicionaPrato(pratoCircular));
		Assert.assertTrue(estoque.adicionaPrato(pratoCircular));
		Assert.assertTrue(estoque.adicionaPrato(pratoTriangular));
		
		Assert.assertEquals(3, estoque.getQtdPratos());
		
		Assert.assertTrue(estoque.removePrato(pratoCircular));
		Assert.assertTrue(estoque.removePrato(pratoTriangular));
		Assert.assertFalse(estoque.removePrato(pratoRetangular));
		
		Assert.assertEquals(1, estoque.getQtdPratos());
		
		//extra: crie testes para o metodo contem Personalizacao 
		//       e recupera prato por personalizacao
	}
	
	@Test
	public void testPasso5e6() throws Exception{
		estoque = new EstoquePratos();
		PratoPersonalizado pratoCircular = estoque.criaPratoCircular(6.0, "Estampa", 30);
		PratoPersonalizado pratoRetangular = estoque.criaPratoRetangular(4.5, "Foto", 25, 30);
		PratoPersonalizado pratoTriangular = estoque.criaPratoTriangular(8.20, "Estampa", 30, 30);
		
		Assert.assertTrue(estoque.adicionaPrato(pratoCircular));
		Assert.assertTrue(estoque.adicionaPrato(pratoRetangular));
		Assert.assertTrue(estoque.adicionaPrato(pratoTriangular));
		
		Assert.assertEquals(3, estoque.getQtdPratos(), 0.01);

		List<PratoPersonalizado> pratos = estoque.getPratosOrdenadosPorPreco();
		
		Assert.assertEquals(12.0, pratos.get(0).calculaPrecoFinal(), 0.01);
		Assert.assertEquals(12.7, pratos.get(1).calculaPrecoFinal(), 0.01);
		Assert.assertEquals(34.26, pratos.get(2).calculaPrecoFinal(), 0.01);		
	}
	
	@Test
	public void testExceptionPreco(){
		try {
			PratoPersonalizado prato = estoque.criaPratoCircular(-15.9, "Estampa", 10);
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals("Preco base nao pode ser negativo.", e.getMessage());
		}
		
		try {
			PratoPersonalizado prato = estoque.criaPratoCircular(8.5, "Mosaico", 10);
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals("Nao criamos pratos com Mosaico. Por favor, escolha outra opcao.", e.getMessage());
		}

		try {
			PratoPersonalizado prato = estoque.criaPratoCircular(8.5, "Estampa", -12);
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals("Dimensao nao pode ser negativa.", e.getMessage());
		}

		try {
			PratoPersonalizado prato = estoque.criaPratoTriangular(8.5, "Estampa", -12, 25);
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals("Dimensao nao pode ser negativa.", e.getMessage());
		}

		try {
			PratoPersonalizado prato = estoque.criaPratoTriangular(8.5, "Estampa", 12, -25);
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals("Dimensao nao pode ser negativa.", e.getMessage());
		}
		
		try {
			PratoPersonalizado prato = estoque.criaPratoRetangular(8.5, "Estampa", -12, 25);
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals("Dimensao nao pode ser negativa.", e.getMessage());
		}

		try {
			PratoPersonalizado prato = estoque.criaPratoRetangular(8.5, "Estampa", 12, -25);
			Assert.fail();
		} catch (Exception e) {
			Assert.assertEquals("Dimensao nao pode ser negativa.", e.getMessage());
		}

	}
}
