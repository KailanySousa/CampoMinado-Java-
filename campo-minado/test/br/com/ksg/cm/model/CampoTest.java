package br.com.ksg.cm.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.ksg.cm.excecao.ExplosaoException;
import br.com.ksg.cm.model.Campo;

class CampoTest {

	private Campo campo;
	
	// anotação utilizada para indicar que a metódo deve ser chamada
	// antes de executar as metódos de teste
	@BeforeEach
	void iniciarCampo() {
		this.campo  = new Campo(3,3);
	}
	
	@Test
	void testeVizinhoEsquerda() {
		
		Campo vizinho = new Campo(3,2);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
		
	}
	
	@Test
	void testeVizinhoDistancia1Direita() {
		
		Campo vizinho = new Campo(3,4);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
		
	}
	
	@Test
	void testeVizinhoAcima() {
		
		Campo vizinho = new Campo(2,3);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
		
	}
	
	@Test
	void testeVizinhoAbaixo() {
		
		Campo vizinho = new Campo(4,3);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
		
	}
	
	@Test
	void testeVizinhoDistancia2() {
		
		Campo vizinho = new Campo(2,2);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertTrue(resultado);
		
	}
	
	@Test
	void testeNaoVizinho() {
		
		Campo vizinho = new Campo(1,1);
		boolean resultado = campo.adicionarVizinho(vizinho);
		assertFalse(resultado);
		
	}
	
	@Test
	void testeAlternarMarcacao() {
		campo.alternarMarcacao();
		assertTrue(campo.isMarcado());
	}
	
	@Test
	void testeAbrirCampoValido() {
		assertTrue(campo.abrir());
	}
	
	@Test
	void testeAbrirNaoMinadoMarcado() {
		campo.alternarMarcacao();
		assertFalse(campo.abrir());
	}
	
	@Test
	void testeAbrirMinadoMarcado() {
		campo.alternarMarcacao();
		campo.minar();
		assertFalse(campo.abrir());
	}
	
	@Test
	void testeAbrirMinadoNaoMarcado() {
		campo.minar();
		assertThrows(ExplosaoException.class, () -> {
			campo.abrir();
		});
	}
	
	@Test
	void testeAbrirComVizinhos() {
		
		Campo campo22 = new Campo(2,2);
		
		Campo campo12 = new Campo(1,2);
		campo12.minar();
		
		Campo campo11 = new Campo(1, 1);
		
		campo22.adicionarVizinho(campo11);
		campo22.adicionarVizinho(campo12);
		
		campo.adicionarVizinho(campo22);
		campo.abrir();
		
		assertTrue(campo22.isAberto() && campo11.isFechado());
	}
	
}
