
/*
 * Universidade Federal do Rio de Janeiro
 * Computacao II - 2016.2
 * Professor Fabio Mascarenhas
 * Jogo Asteroids - Trabalho 1
 * Aluno : Ruan da Fonseca Ramos - DRE 111309866
 */

import java.util.Set;
import java.util.HashSet;

public class Jogo {
	Cor corAsteroide = new Cor(Math.random(), Math.random(), Math.random());
	Nave nave;
	HashSet<Asteroide> asteroides = new HashSet<Asteroide>();
	HashSet<Tiro> tiros = new HashSet<Tiro>();

	public int vidas = 3;
	int score = 0;
	Timer timerTiro;

	boolean pausa;

	public Jogo() {
		inicializaJogo();
	}

	public String getTitulo() {
		return "Asteroids";
	}

	public int getLargura() {
		return 800;
	}

	public int getAltura() {
		return 600;
	}

	public void tique(Set<String> keys, double dt) {
		if (pausa) {
			return;
		}
		timerTiro.tique(dt);
		atualizaPosicaoObjetos(dt);
		// como nao posso modificar os hashsets enquanto percorro, criei um
		// segundo hashset para guardar os dados de tiros e asteroides
		HashSet<Asteroide> atingidos = new HashSet<Asteroide>();
		HashSet<Tiro> tirosConcluidos = new HashSet<Tiro>();

		for (Tiro tiro : tiros) {
			if (!tiro.checaPosicao(getLargura(), getAltura())) {
				tirosConcluidos.add(tiro);
			}
			for (Asteroide asteroide : asteroides) {
				if (colisaoTiroAsteroide(asteroide, tiro)) {
					atingidos.add(asteroide);
					score += 100;
					tirosConcluidos.add(tiro);
				}
			}
		}

		for (Asteroide asteroide : asteroides) {
			if (colisaoNaveAsteroide(nave, asteroide)) {
				// mudo o tamanho para que ele nao seja dividido depois
				asteroide.tamanho = 1;
				atingidos.add(asteroide);
				vidas -= 1;
				nave = new Nave(this, getLargura() / 2.0, getAltura() / 2.0, 0.0, 0.0, -Math.PI / 2, false);
			}
		}
		for (Tiro tiro : tirosConcluidos) {
			tiros.remove(tiro);
		}

		for (Asteroide asteroide : atingidos) {
			if (asteroide.tamanho > 2) {
				divideAsteroide(asteroide);
			}
			asteroides.remove(asteroide);
			if (asteroides.isEmpty()) {
				criaAsteroides();
			}
		}

		// nave fica imovel apos chegar a zero vidas
		if (keys.contains("left") && vidas > 0) {
			nave.giraEsquerda(dt);
		}
		if (keys.contains("right") && vidas > 0) {
			nave.giraDireita(dt);
		}
		if (keys.contains("up") && vidas > 0) {
			nave.acelera(dt);
		} else {
			nave.para(dt);
		}
	}

	private void inicializaJogo() {
		asteroides.clear();
		tiros.clear();
		vidas = 3;
		score = 0;
		// o angulo de -PI/2 foi para manter a orientacao correta, ja que o
		// sprite aponta para cima
		nave = new Nave(this, getLargura() / 2.0, getAltura() / 2.0, 0.0, 0.0, -Math.PI / 2, false);
		criaAsteroides();
		timerTiro = new Timer(0.1);
	}

	// funcao que move asteroides e tiros
	private void atualizaPosicaoObjetos(double dt) {
		for (Asteroide asteroide : asteroides) {
			asteroide.mover(dt);
			asteroide.rodar(dt);
		}
		for (Tiro tiro : tiros) {
			tiro.mover(dt);
		}
	}

	public void criaAsteroides() {
		for (int i = 0; i < 6; i++) {
			Asteroide asteroide;
			do {
				asteroide = new Asteroide(this, (double) (Math.random() * getLargura() + 1),
						(double) (Math.random() * getAltura() + 1), (int) (Math.random() * 4) + 1, corAsteroide,
						-300 + ((double) (Math.random() * 601)), -300 + ((double) (Math.random() * 601)),
						(double) -(Math.PI) * 2 + (Math.random() * ((2 * (Math.PI)) * 2 + 1)));
			} while (colisaoNaveAsteroideSemSom(nave, asteroide, 200));
			asteroides.add(asteroide);
		}
	}

	// funcao para quando aperta uma tecla
	public void tecla(String c) {
		if (c.equals("p")) {
			pausa = !pausa;
		}
		if (pausa)
			return;

		if (c.equals("r")) {
			this.inicializaJogo();
		}

		// se a vida esta em zero fica impossibilitado de atirar
		if (c.equals(" ") && vidas > 0) {
			// diminuindo a frequencia de tiros ao apertar espaco com um timer
			if (timerTiro.done()) {
				Motor.tocar("shoot.wav");
				tiros.add(new Tiro(nave.x + Nave.alt / 2 * Math.cos(nave.angulo),
						nave.y + Nave.alt / 2 * (Math.sin(nave.angulo) - 1), 100 * Math.cos(nave.angulo),
						100 * Math.sin(nave.angulo)));
				timerTiro.reset();
			}
		}
	}

	public void desenhar(Tela t) {
		t.texto("Score: " + score, 570, 35, 30, Cor.BRANCO);
		t.texto("Vidas: " + vidas, 20, 35, 30, Cor.BRANCO);
		for (Asteroide asteroide : asteroides) {
			asteroide.desenhar(t);
		}
		nave.desenhar(t);
		for (Tiro tiro : tiros) {
			tiro.desenhar(t, nave);
		}

		if (vidas <= 0) {
			gameOver(t);
			nave = new Nave(this, -200, -200, 0.0, 0.0, -Math.PI / 2, false);
		}
	}

	// para calcular distancias entre pontos: d^2 = (x2-x1)^2 + (y2-y1)^2
	private static double distanciaAoQuadrado(double x1, double y1, double x2, double y2) {
		return (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1);
	}

	// calcula colisao entre tiro e asteroide
	public boolean colisaoTiroAsteroide(Asteroide asteroide, Tiro tiro) {
		if (asteroide.getRaio() * asteroide.getRaio() >= distanciaAoQuadrado(asteroide.x,
				asteroide.y - asteroide.getAltura() / 2, tiro.x, tiro.y)) {
			Motor.tocar("explosion.wav");
			return true;
		}
		return false;
	}

	// divide os asteroides atingidos por tiros
	public void divideAsteroide(Asteroide asteroide) {
		double vx = -300 + ((double) (Math.random() * 601));
		double vy = -300 + ((double) (Math.random() * 601));
		asteroides.add(new Asteroide(this, asteroide.x, asteroide.y, 1, corAsteroide, -vx, -vy,
				(double) -(Math.PI) * 2 + (Math.random() * ((2 * (Math.PI)) * 2 + 1))));
		asteroides.add(new Asteroide(this, asteroide.x, asteroide.y, asteroide.tamanho / 2, corAsteroide, vx, vy,
				(double) -(Math.PI) * 2 + (Math.random() * ((2 * (Math.PI)) * 2 + 1))));
	}

	// calcula colisao entre nave e asteroides
	public boolean colisaoNaveAsteroide(Nave nave, Asteroide asteroide, int raio) {
		if (((asteroide.getRaio() + raio) * (asteroide.getRaio() + raio)) >= distanciaAoQuadrado(asteroide.x,
				asteroide.y - asteroide.getAltura() / 2, nave.x, nave.y - Nave.alt / 2)) {
			Motor.tocar("explosion.wav");
			return true;
		}
		return false;
	}

	public boolean colisaoNaveAsteroide(Nave nave, Asteroide asteroide) {
		return colisaoNaveAsteroide(nave, asteroide, 5);
	}

	// calcula a colisao sem o som da explosao e recebendo um raio, que serve
	// para nao serem criados meteoros diretamente em cima da nave, e sim fora
	// desse raio especificado
	public boolean colisaoNaveAsteroideSemSom(Nave nave, Asteroide asteroide, int raio) {
		if (((asteroide.getRaio() + raio) * (asteroide.getRaio() + raio)) >= distanciaAoQuadrado(asteroide.x,
				asteroide.y - asteroide.getAltura() / 2, nave.x, nave.y - Nave.alt / 2)) {
			return true;
		}
		return false;
	}

	public void gameOver(Tela t) {
		t.texto("GAME OVER", 60, (double) (getAltura() / 2 + 10), 110, Cor.BRANCO);
		t.texto("Your Score: " + score, 100, 430, 60, Cor.BRANCO);
		t.texto("Aperte R para reiniciar", getLargura() / 4, getAltura() - 40, 40, Cor.BRANCO);
	}

	public static void main(String[] args) {
		new Motor(new Jogo());
	}

}
