/*
 * Universidade Federal do Rio de Janeiro
 * Computacao II - 2016.2
 * Professor Fabio Mascarenhas
 * Jogo Flappy Bird - Trabalho 2
 * Aluno : Ruan da Fonseca Ramos - DRE 111309866
 */

public class Passaro {
	double x; // posicao do centro
	double y; // base do passaro
	double vy;
	double angulo;
	double teste = 0;
	int estado = 0; // essa variavel guarda qual dos 3 desenhos vai ser colocado
					// na tela (posicao das asas difere em cada). Vale 0 para
					// asa em cima, 1 no meio e 2 embaixo.
	boolean vivo;
	public static final int larg = 33;
	public static final int alt = 27;

	Timer timerPassaro = new Timer(0.05, true, new Acao() {
		public void executa() {
			if (vy != 0) {
				mudaEstado();
			}
		}
	});
	Hitbox hitbox;

	public Passaro(double x, double y, double vy, double angulo, boolean vivo) {
		this.x = x;
		this.y = y;
		this.vy = vy;
		this.angulo = angulo;
		this.vivo = vivo;
		hitbox = new Hitbox(x - (larg - 3) / 2, y - alt, x + (larg - 3) / 2, y);
	}

	// Muda o atributo estado do passaro que indica a posicao da asa
	public void mudaEstado() {
		estado = (estado + 1) % 3;
	}

	// move o passaro
	public void mover(double dt) {
		if (!foraDaTela()) {
			if (vivo) {
				y += vy * dt;
				hitbox.mover(0.0, vy * dt);
			} else {
				if (y <= 600 - Chao.alt) {
					y += vy * dt;
					hitbox.mover(0.0, vy * dt);
				} else {
					vy = 0;
				}
			}
		} else {
			y += 0.1;
			hitbox.mover(0.0, 0.1);
		}
	}

	public void morrer() {
		vivo = false;
	}

	// Faz o movimento de rotacao do passaro
	public void girar(double dt) {
		if (y <= 600 - Chao.alt && vy != 0) {
			angulo += Math.PI / 3 * dt;
			while (angulo > 2 * (Math.PI)) {
				angulo -= 2 * (Math.PI);
			}
			while (angulo < 0) {
				angulo += 2 * (Math.PI);
			}
		}
	}

	// Checa se o passaro esta fora da tela
	public boolean foraDaTela() { // retorna true se ele saiu da tela
		if (y - alt <= 0) {
			return true;
		} else {
			return false;
		}
	}

	// Desenha o passaro na tela
	public void desenhar(Tela t) {
		int[] xArquivo = new int[] {526, 526, 443};
		int[] yArquivo = new int[] {125, 177, 244};
		t.imagem("flappy.png", xArquivo[estado], yArquivo[estado], larg, alt, angulo, x - larg / 2, y - alt);
	}
}
