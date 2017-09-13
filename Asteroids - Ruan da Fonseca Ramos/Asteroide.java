/*
 * Universidade Federal do Rio de Janeiro
 * Computacao II - 2016.2
 * Professor Fabio Mascarenhas
 * Jogo Asteroids - Trabalho 1
 * Aluno : Ruan da Fonseca Ramos - DRE 111309866
 */

public class Asteroide {
	double x; // meio do asteroide
	double y; // base do asteroide
	int tamanho; // 1, 2, 3 ou 4
	Cor cor;
	double vx;
	double vy;
	double vRot;
	double angulo;
	// fiz vetores com valores estaticos para facilitar o acesso posterior em
	// funcao do tamanho do asteroide
	private static final int[] larg = new int[] { 0, 8, 16, 31, 47 };
	private static final int[] alt = new int[] { 0, 10, 15, 30, 46 };
	private static final int[] raio = new int[] { 0, 5, 7, 15, 23 };
	private static final int[] xArquivo = new int[] { 0, 2, 16, 33, 65 };
	private static final int[] yArquivo = new int[] { 2, 45, 95, 144, 192, 241, 289, 336, 384, 432, 480 };
	int randomNumber = (int) (Math.random() * 11);

	Jogo jogo;

	public int getLargura() {
		return larg[this.tamanho];
	}

	public int getAltura() {
		return alt[this.tamanho];
	}

	public int getRaio() {
		return raio[this.tamanho];
	}

	public int getXArquivo() {
		return xArquivo[this.tamanho];
	}

	public int getYArquivo() {
		return yArquivo[this.randomNumber];
	}

	public Asteroide(Jogo jogo, double x, double y, int tamanho, Cor cor, double vx, double vy, double vRot) {
		this.x = x;
		this.y = y;
		this.tamanho = tamanho;
		this.cor = cor;
		this.vx = vx;
		this.vy = vy;
		this.vRot = vRot;
		this.angulo = 0;
	}

	// atualiza posicao do asteroide
	public void mover(double dt) {
		checaPosicao();
		x += vx * dt;
		y += vy * dt;
	}

	// gira o asteroide
	public void rodar(double dt) {
		angulo += vRot * dt;
		while (angulo > 360) {
			angulo -= 360;
		}
		while (angulo < 0) {
			angulo += 360;
		}
	}

	public int tamanhoDoAsteroide() {
		return tamanho;
	}

	// checa se o asteroide vai sair da tela e passa pro outro lado
	public void checaPosicao() {
		if (vx > 0 && x >= 800 + getLargura() / 2) {
			x = 0 - getLargura() / 2;
		}
		if (vx < 0 && x <= 0 - getLargura() / 2) {
			x = 800 + getLargura() / 2;
		}
		if (vy < 0 && y <= 0 - getAltura() / 2) {
			y = 600 + getAltura() / 2;
		}
		if (vy > 0 && y >= 600 + getAltura() / 2) {
			y = 0 - getAltura() / 2;
		}
	}

	public void desenhar(Tela t) {
		t.imagem("asteroids.png", getXArquivo(), getYArquivo(), getLargura(), getAltura(), angulo, x - getLargura() / 2,
				y - getAltura());
	}
}
