/*
 * Universidade Federal do Rio de Janeiro
 * Computacao II - 2016.2
 * Professor Fabio Mascarenhas
 * Jogo Flappy Bird - Trabalho 2
 * Aluno : Ruan da Fonseca Ramos - DRE 111309866
 */

import java.util.HashSet;

public class FlappyBird implements Jogo {
	Passaro passaro;
	int vidas = 1;
	int score = 0;
	int centena, dezena, unidade, digito;
	int highScore = 0;
	HashSet<Cano> canosDentro = new HashSet<Cano>();
	HashSet<Chao> pedacosChao = new HashSet<Chao>();
	private static final int xArquivoCenario = 0;
	private static final int yArquivoCenario = 0;
	private static final int largCenario = 288;
	private static final int altCenario = 510;
	public FlappyBird() {
		iniciaJogo();
	}

	public String getTitulo() {
		return "FlappyBird";
	}

	public int getLargura() {
		return 800;
	}

	public int getAltura() {
		return 600;
	}
	
	// Cria os elementos iniciais do jogo
	public void iniciaJogo() {
		passaro = new Passaro(getLargura() / 6, getAltura() / 2, 1, 0.0, true);
		for(int i = 0; i < 5; i++) {
			Chao chao = new Chao(Chao.larg * i);
			pedacosChao.add(chao);
		}
		
		
		for (int i = 0; i < 200; i++) {
			int altRandom = 271 - (int) (Math.random() * 131);
			// Criando canos de cima
			Cano canoCima = new Cano((getLargura() + Cano.larg / 2) + 200 * i, altRandom, altRandom, false);
			canosDentro.add(canoCima);
			// Criando canos de baixo
			Cano canoBaixo;
			canoBaixo = new Cano((getLargura() + Cano.larg / 2) + 200 * i, canoCima.altura + Cano.gap,
					getAltura() - Chao.alt - canoCima.altura - Cano.gap, true);
			canosDentro.add(canoBaixo);
		}
	}
	
	// Checa se o passaro passou por um cano
	public boolean passouCano(Passaro p, Cano c) {
		if(p.x - Passaro.larg >= c.x) {
			return true;
		} else {
			return false;
		}
	}
	
	// Checa se o passaro colidiu com o cano
	public boolean colidiuCano(Passaro p, Cano c) {
		if(p.hitbox.intersecao(c.hitbox) > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	// Checa se o passaro colidiu com o caho
	public boolean colidiuChao(Passaro p, Chao c) {
		if(p.hitbox.intersecao(c.hitbox) > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	// Da as mensagens de game over na tela
	public void printGameOver(Tela t) {
		t.imagem("flappy.png", Chao.xArquivo, 395, 188, 42, 0.0, largCenario, altCenario / 7);
		t.texto("Pressione R para reiniciar", 150, 260, 40, Cor.BRANCO);
		t.texto("Highscore: " + highScore, 150, 200, 40, Cor.BRANCO);
	}
	
	// Da a mensagem de pause na tela
	public void escrevePause(Tela t) {
		t.texto("Pause", 330, 250, 40, Cor.BRANCO);
	}
	
	// Para a movimentacao dos canos, mudando a velocidade para 0
	public void congelaCanos(HashSet<Cano> c) {
		for(Cano cano : c) {
			cano.vx = 0;
		}
	}
	
	// Para a movimentacao dos pedacos de chao, mudando a velocidade para 0
	public void congelaChao(HashSet<Chao> c) {
		for(Chao chao : c) {
			chao.vx = 0;
		}
	}
	
	// Alterna entre os estados do passaro (posicao da asa)
	public void alternaEstado(double dt) {
		passaro.timerPassaro.tique(dt);
	}
	
	// Divide o score em unidade, centena e dezena
	public void divideScore(double dt) {
		unidade = score % 10 ;
		dezena = (score / 10) % 10;
		centena = (score / 100) % 10;
	}
	
	// Imprime o digito na tela no formato do sprite
	public void printaDigito(Tela tela, int x) {
		switch(digito) {
		case 0:
			tela.imagem("flappy.png", 573, 198, 19, 24, 0.0, x, altCenario / 25 + 1);
			break;
		case 1:
			tela.imagem("flappy.png", 579, 233, 15, 24, 0.0, x, altCenario / 25);
			break;
		case 2:
			tela.imagem("flappy.png", 575, 264, 19, 26, 0.0, x, altCenario / 25);
			break;
		case 3:
			tela.imagem("flappy.png", 575, 297, 19, 24, 0.0, x, altCenario / 25);
			break;
		case 4:
			tela.imagem("flappy.png", 571, 343, 19, 24, 0.0, x, altCenario / 25);
			break;
		case 5:
			tela.imagem("flappy.png", 571, 367, 19, 24, 0.0, x, altCenario / 25);
			break;
		case 6:
			tela.imagem("flappy.png", 328, 487, 18, 24, 0.0, x, altCenario / 25);
			break;
		case 7:
			tela.imagem("flappy.png", 348, 487, 18, 24, 0.0, x, altCenario / 25);
			break;
		case 8:
			tela.imagem("flappy.png", 367, 487, 19, 24, 0.0, x, altCenario / 25);
			break;
		case 9:
			tela.imagem("flappy.png", 388, 488, 19, 24, 0.0, x, altCenario / 25);
			break;
		}
	}
	
	public void tique(java.util.Set<String> teclas, double dt) {
		// esse hashset some a cada tique
		HashSet<Cano> canosFora = new HashSet<Cano>();
		passaro.mover(dt);
		passaro.girar(dt);
		alternaEstado(dt);
		for (Chao chao : pedacosChao) {
			if(colidiuChao(passaro, chao)) {
				passaro.morrer();
				congelaChao(pedacosChao);
				congelaCanos(canosDentro);
				if(vidas > 0) {
					vidas -= 1;
				}
			}
			chao.mover(dt);
			chao.trocaLado();
		}
		if (passaro.vy <= 250 && passaro.vy != 0) {
			passaro.vy += 15;
		}
		for (Cano cano : canosDentro) {
			if(colidiuCano(passaro, cano)) {
				passaro.morrer();
				congelaChao(pedacosChao);
				congelaCanos(canosDentro);
				if(vidas > 0) {
					vidas -= 1;
				}
			}
			if(cano.x < passaro.x - Passaro.larg / 2 && cano.ultrapassado == false && cano.embaixo) {
				score ++;
				cano.ultrapassado = true;
			}
			cano.mover(dt);
			if (!cano.estaDentro()) {
				canosFora.add(cano);
			}
		}
		if(score >= highScore) {
			highScore = score;
		}
		for (Cano cano : canosFora) {
			canosDentro.remove(cano);
		}
		divideScore(dt);
	}
	
	
	
	public void tecla(String tecla) {
		if (tecla.equals(" ") && passaro.vivo && passaro.vy != 0) {
			passaro.vy = -550; // velocidade de subida
			passaro.angulo = -Math.PI / 6; // rotacao do passaro
		}
		
		if (tecla.equals("p") && passaro.vivo) { // o passaro so tem velocidade 0 no pause
			if(passaro.vy != 0) {
				passaro.vy = 0;
				for(Cano cano : canosDentro) {
					cano.vx = 0;
				}
				for(Chao chao : pedacosChao) {
					chao.vx = 0;
				}
			} else {
				passaro.vy = 1;
				for(Cano cano : canosDentro) {
					cano.vx = -200;
				}
				for(Chao chao : pedacosChao) {
					chao.vx = -200;
				}
			}
		}
		
		if (tecla.equals("r") && !passaro.vivo) {
			canosDentro.clear();
			pedacosChao.clear();
			vidas = 1;
			score = 0;
			iniciaJogo();
		}
	}

	// Desenha todos os elementos na tela
	public void desenhar(Tela tela) {		
		for (int i = 0; i < 3; i++) {
			tela.imagem("flappy.png", xArquivoCenario, yArquivoCenario, largCenario, altCenario, 0.0, i * largCenario,
					0.0);
		}
		for(Chao chao : pedacosChao) {
			chao.desenhar(tela);
		}

		for (Cano cano : canosDentro) {
			cano.desenhar(tela);
		}

		passaro.desenhar(tela);
		
		if(vidas <= 0) {
			printGameOver(tela);
		}
		
		if(passaro.vy == 0 && passaro.vivo) {
			escrevePause(tela);
		}
		
		digito = unidade;
		printaDigito(tela, largCenario + 100);
		digito = dezena;
		printaDigito(tela, largCenario + 80);
		digito = centena;
		printaDigito(tela, largCenario + 60);
		
		if(passaro.vivo) {
			tela.texto("Pause: pressione p", 10, 30, 15, Cor.BRANCO);
		}
		
		
	}

	public static void main(String[] args) {
		new Motor(new FlappyBird());
	}
}
