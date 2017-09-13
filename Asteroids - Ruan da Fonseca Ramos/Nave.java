/*
 * Universidade Federal do Rio de Janeiro
 * Computacao II - 2016.2
 * Professor Fabio Mascarenhas
 * Jogo Asteroids - Trabalho 1
 * Aluno : Ruan da Fonseca Ramos - DRE 111309866
 */

public class Nave {
	double x; // centro da nave
	double y; // base da nave
	double vx;
	double vy;
	double angulo; // angulo da direção da nave sentido horario)
	boolean motorLigado;

	static int larg = 50;
	static int alt = 60;

	public Nave(Jogo jogo, double x, double y, double vx, double vy, double angulo, boolean motorLigado) {
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
		this.angulo = angulo;
		this.motorLigado = motorLigado;
	}

	public boolean ligada() {
		// tinha colocado uma comparação de double com 0, então troquei para uma
		// comparação de valor absoluto com um valor muito pequeno
		if (Math.abs(vx) > 1e-9 || Math.abs(vy) > 1e-9) {
			return true;
		}
		return false;
	}

	public void mover(double dt) {
		checaPosicao();
		x += vx * dt;
		y += vy * dt;
	}

	// checa se a nave sai da tela e passa para o outro lado
	public void checaPosicao() {
		if (vx > 0 && x >= 800 + larg / 2) {
			x = 0 - larg / 2;
		}
		if (vx < 0 && x <= 0 - larg / 2) {
			x = 800 + larg / 2;
		}
		if (vy < 0 && y <= 0 - alt / 2) {
			y = 600 + alt / 2;
		}
		if (vy > 0 && y >= 600 + alt / 2) {
			y = 0 - alt / 2;
		}
	}

	// funcao para acelerar a nave com a seta para cima
	public void acelera(double dt) {
		vx = 100 * Math.cos(this.angulo);
		vy = 100 * Math.sin(this.angulo);
		mover(dt);
	}

	// funcao para parar o movimento da nave
	public void para(double dt) {
		vx = 0;
		vy = 0;
		motorLigado = false;
	}

	// funcoes para girar a nave para os lados com as setas
	public void giraDireita(double dt) {
		angulo += Math.PI * dt;
		while (angulo > 2 * (Math.PI)) {
			angulo -= 2 * (Math.PI);
		}
		while (angulo < 0) {
			angulo += 2 * (Math.PI);
		}
	}

	public void giraEsquerda(double dt) {
		angulo -= Math.PI * dt;
		while (angulo > 2 * (Math.PI)) {
			angulo -= 2 * (Math.PI);
		}
		while (angulo < 0) {
			angulo += 2 * (Math.PI);
		}
	}

	public void desenhar(Tela t) {
		int xArquivo;
		int yArquivo;
		if (!ligada()) {
			xArquivo = 70;
			yArquivo = 0;
		} else {
			xArquivo = 135;
			yArquivo = 0;
		}
		t.imagem("naves.png", xArquivo, yArquivo, larg, alt, this.angulo + Math.PI / 2, x - larg / 2, y - alt);
	}
}
