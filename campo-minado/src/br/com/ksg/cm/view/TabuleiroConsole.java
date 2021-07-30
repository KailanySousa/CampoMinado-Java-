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
			
			// enquanto o usuário informar que quer jogar novamente, o tabuleiro será reiniciado
			while(continuar) {
				System.out.println("Jogar novamente? (S/n)");
				String resposta = entrada.nextLine();
				
				// verifica a opção informada pelo usuário
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
			
			// enquanto o objetivo não for alcançado o jogo continua
			while(!this.tabuleiro.objetivoAlcancado()) {
				
				System.out.println(this.tabuleiro); // exibindo o tabuleiro
				
				// resgatando valor digitado pelo usuário
				String digitado = this.getValorDigitado("Para sair digite 'sair'\nInforme o campo (x, y):");
			
				/*	
				 * 	resgatando os valores da linha e coluna informados pelo usuário
				 * 	para abrir os campos
				 */ 
				int linha = Integer.parseInt(digitado.split(",")[0].trim());
				int coluna = Integer.parseInt(digitado.split(",")[1].trim());
				
				// resgatando valor digitado pelo usuário
				digitado = this.getValorDigitado("1 - Abrir\n2 - (Des)Marcar: ");
			
				// verifica a opção informada pelo usuário
				if("1".equals(digitado)) {
					this.tabuleiro.abrir(linha, coluna); // abrindo um campo
				} else if("2".equals(digitado)) {
					this.tabuleiro.marcar(linha, coluna); // marcando um campo
				}
			}
			
			System.out.println(this.tabuleiro);
			System.out.println("Você ganhou!");
		} catch (ExplosaoException e) {
			System.out.println(this.tabuleiro);
			System.out.println("Você perdeu!");
		}
		
	}
	
	/**
	 * @param texto - mensagem a ser exibida para que o usuário informe a sua ação no jogo
	 * @return o metódo retorna o valor que foi informado pelo usuário
	 * */
	private String getValorDigitado(String texto) {
		System.out.print(texto);
		
		String digitado = entrada.nextLine();
		
		// verifica se o usuário informou a opção "sair"
		if("sair".equalsIgnoreCase(digitado)) {
			throw new SairException();
		}
		
		return digitado;
	}
}
