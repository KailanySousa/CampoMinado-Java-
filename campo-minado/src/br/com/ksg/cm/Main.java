package br.com.ksg.cm;

import java.util.Scanner;

import br.com.ksg.cm.model.Tabuleiro;
import br.com.ksg.cm.view.TabuleiroConsole;

public class Main {

	public static void main(String[] args) {
		
		Scanner entrada = new Scanner(System.in);
		
		System.out.println("Informe a quantidade de linhas: ");
		int linhas = entrada.nextInt();
		
		System.out.println("Informe a quantidade de colunas: ");
		int colunas = entrada.nextInt();
		
		System.out.println("Informe a quantidade de minas: ");
		int minas = entrada.nextInt();
		
		Tabuleiro tabuleiro = new Tabuleiro(linhas,colunas,minas);
		
		new TabuleiroConsole(tabuleiro);

	}

}
