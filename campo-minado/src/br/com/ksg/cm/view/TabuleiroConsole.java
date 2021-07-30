package br.com.ksg.cm.view;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import br.com.ksg.cm.excecao.ExplosaoException;
import br.com.ksg.cm.excecao.SairException;
import br.com.ksg.cm.model.Tabuleiro;

public class TabuleiroConsole {

	private Tabuleiro tabuleiro;
	private Scanner entrada = new Scanner(System.in);
	
	public TabuleiroConsole(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		
		this.executarJogo();
	}

	private void executarJogo() {
		
		try {
			this.cicloJogo();
			
			boolean continuar = true;
			
			// enquanto o usu�rio informar que quer jogar novamente, o tabuleiro ser� reiniciado
			while(continuar) {
				System.out.println("Jogar novamente? (S/n)");
				String resposta = entrada.nextLine();
				
				// verifica a op��o informada pelo usu�rio
				if("n".equalsIgnoreCase(resposta)) {
					continuar = false;
				} else if("s".equalsIgnoreCase(resposta)) {
					this.tabuleiro.reiniciar();
					this.executarJogo();
				}
			}
			
		} catch (SairException e) {
			System.out.println("Tchau!");
		} finally {
			entrada.close();
		}
		
	}

	private void cicloJogo() {
		try {
			
			// enquanto o objetivo n�o for alcan�ado o jogo continua
			while(!this.tabuleiro.objetivoAlcancado()) {
				
				System.out.println(this.tabuleiro); // exibindo o tabuleiro
				
				// resgatando valor digitado pelo usu�rio
				String digitado = this.getValorDigitado("Para sair digite 'sair'\nInforme o campo (x, y):");
			
				/*	
				 * 	resgatando os valores da linha e coluna informados pelo usu�rio
				 * 	para abrir os campos
				 */ 
				int linha = Integer.parseInt(digitado.split(",")[0].trim());
				int coluna = Integer.parseInt(digitado.split(",")[1].trim());
				
				// resgatando valor digitado pelo usu�rio
				digitado = this.getValorDigitado("1 - Abrir\n2 - (Des)Marcar: ");
			
				// verifica a op��o informada pelo usu�rio
				if("1".equals(digitado)) {
					this.tabuleiro.abrir(linha, coluna); // abrindo um campo
				} else if("2".equals(digitado)) {
					this.tabuleiro.marcar(linha, coluna); // marcando um campo
				}
			}
			
			System.out.println(this.tabuleiro);
			System.out.println("Voc� ganhou!");
		} catch (ExplosaoException e) {
			System.out.println(this.tabuleiro);
			System.out.println("Voc� perdeu!");
		}
		
	}
	
	/**
	 * @param texto - mensagem a ser exibida para que o usu�rio informe a sua a��o no jogo
	 * @return o met�do retorna o valor que foi informado pelo usu�rio
	 * */
	private String getValorDigitado(String texto) {
		System.out.print(texto);
		
		String digitado = entrada.nextLine();
		
		// verifica se o usu�rio informou a op��o "sair"
		if("sair".equalsIgnoreCase(digitado)) {
			throw new SairException();
		}
		
		return digitado;
	}
}
