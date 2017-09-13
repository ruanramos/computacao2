
public class Ponto extends Figura{
	
	public Ponto(int x, int y) {
		super(x,y);
	}
	
	public void desenhar(DesenhoFigura df) {
		df.desenhar(this);
	}
	
	public boolean dentro(int x, int y) {
		return (this.x == x && this.y == y);
	}
	
	public String toString() {
		return x + ", " + y; 
	}
}
