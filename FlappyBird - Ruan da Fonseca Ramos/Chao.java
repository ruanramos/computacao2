/*
 * Universidade Federal do Rio de Janeiro
 * Computacao II - 2016.2
 * Professor Fabio Mascarenhas
 * Jogo Flappy Bird - Trabalho 2
 * Aluno : Ruan da Fonseca Ramos - DRE 111309866
 */

public class Chao {
	double x;
	double vx = -200;
	public static final int xArquivo = 295;
	private static final int yArquivo = 0;
	public static final int larg = 200;
	public static final int alt = 113;

	Hitbox hitbox;

	public Chao(double x) {
		this.x = x;
		hitbox = new Hitbox(x, 600 - alt, x + larg, 600);
	}

	// Move o chao
	public void mover(double dt) {
		x += vx * dt;
		hitbox.mover(vx * dt, 0.0);
	}

	// Passa o chao para o outro lado da tela caso ele saia
	public void trocaLado() {
		if (x <= -larg) {
			x = 800;
		}
	}

	// Desenha o chao
	public void desenhar(Tela t) {
		t.imagem("flappy.png", xArquivo, yArquivo, larg + 3, alt, 0.0, x, 600 - alt);
	}
}
