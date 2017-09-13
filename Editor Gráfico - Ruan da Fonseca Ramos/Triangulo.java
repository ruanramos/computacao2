
public class Triangulo extends Figura {
	Ponto p1, p2, p3;

	public Triangulo(Ponto p1, Ponto p2, Ponto p3) {
		super(p1.x, p1.y);
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
	}

	public void desenhar(DesenhoFigura df) {
		df.desenhar(this);
	}

	public boolean dentro(int x, int y) {
		boolean b1 = sinal(x, y, p1, p2);
		boolean b2 = sinal(x, y, p2, p3);
		boolean b3 = sinal(x, y, p3, p1);
		return ((b1 == b2) && (b2 == b3));
	}

	public void mover(int dx, int dy) {
		this.p1.mover(dx, dy);
		this.p2.mover(dx, dy);
		this.p3.mover(dx, dy);
	}

	// produto vetorial
	private boolean sinal(int x, int y, Ponto p2, Ponto p3) {
		return ((x - p3.x) * (p2.y - p3.y) - (p2.x - p3.x) * (y - p3.y) <= 0);
	}
}
