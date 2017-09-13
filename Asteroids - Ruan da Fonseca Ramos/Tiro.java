/*
 * Universidade Federal do Rio de Janeiro
 * Computacao II - 2016.2
 * Professor Fabio Mascarenhas
 * Jogo Asteroids - Trabalho 1
 * Aluno : Ruan da Fonseca Ramos - DRE 111309866
 */

public class Tiro {
	double x; // centro do tiro
	double y; // centro do tiro
	double vx;
	double vy;

	static int raio = 1;

	public Tiro(double x, double y, double vx, double vy) {
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
	}

	// Atualiza posicao do tiro
	public void mover(double dt) {
		x += vx * dt;
		y += vy * dt;
	}

	// checa se o tiro saiu da tela: true dentro da tela, false fora da tela
	public boolean checaPosicao(int largura, int altura) {
		if (x + raio < 0 || x - raio > largura || y + raio < 0 || y - raio > altura) {
			return false;
		}
		return true;

	}

	public void desenhar(Tela t, Nave nave) {
		t.circulo(x, y, raio, Cor.BRANCO);
	}
}
