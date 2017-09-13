/*
 * Universidade Federal do Rio de Janeiro
 * Computacao II - 2016.2
 * Professor Fabio Mascarenhas
 * Jogo Asteroids - Trabalho 1
 * Aluno : Ruan da Fonseca Ramos - DRE 111309866
 */

public class Timer {
	double tempo;
	double limite;

	public Timer(double limite) {
		this.limite = limite;
	}

	public boolean tique(double dt) {
		tempo += dt;
		return tempo > limite;
	}

	public boolean done() {
		return tempo > limite;
	}

	public void reset() {
		tempo = 0.0;
	}
}