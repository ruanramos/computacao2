public abstract class Figura {
	int x, y;

	public Figura(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public abstract void desenhar(DesenhoFigura df);

	public abstract boolean dentro(int x, int y);

	public void mover(int dx, int dy) {
		this.x += dx;
		this.y += dy;
	}
}
