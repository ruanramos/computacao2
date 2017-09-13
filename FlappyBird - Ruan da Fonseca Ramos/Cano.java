/*
 * Universidade Federal do Rio de Janeiro
 * Computacao II - 2016.2
 * Professor Fabio Mascarenhas
 * Jogo Flappy Bird - Trabalho 2
 * Aluno : Ruan da Fonseca Ramos - DRE 111309866
 */

public class Cano {
	double x; // posicao do centro
	double y; // base do cano de cima, topo do de baixo
	double vx = -200;
	int altura;
	public boolean embaixo; // true se o cano esta embaixo, false se esta em
							// cima
	public boolean ultrapassado = false;

	public static final int larg = 55;
	public static final int gap = 120;
	public static final int altBaixo = 243;
	public static final int altCima = 657;

	Hitbox hitbox;

	public Cano(double x, double y, int altura, boolean embaixo) {
		this.x = x;
		this.y = y;
		this.embaixo = embaixo;
		this.altura = altura;
		if (embaixo) {
			hitbox = new Hitbox(x - larg / 2, y, x + larg / 2, y + altura);
		} else {
			hitbox = new Hitbox(x - larg / 2, y - altura, x + larg / 2, y);
		}
	}

	// Move o cano
	public void mover(double dt) {
		x += vx * dt;
		hitbox.mover(vx * dt, 0);
	}

	// Ve se o cano saiu para o lado esquerdo da tela
	public boolean estaDentro() {
		return !(x < 0 - larg / 2);
	}

	// Desenha o cano
	public void desenhar(Tela t) {
		int xArquivo;
		int yArquivo;
		if (embaixo) {
			xArquivo = 657;
			yArquivo = 0;
			// 244 é a altura da imagem menor de cano do sprite
			t.imagem("flappy.png", xArquivo, yArquivo, larg, altura, 0.0, x - larg / 2, 600 - altura - Chao.alt); // esse
																													// y
																													// eh
																													// o
																													// topo
		} else {
			xArquivo = 603;
			yArquivo = 0;
			// 271 é a altura da imagem maior de cano do sprite
			t.imagem("flappy.png", xArquivo, 271 - altura, larg, altura, 0.0, x - larg / 2, 0);
		}

	}
}
