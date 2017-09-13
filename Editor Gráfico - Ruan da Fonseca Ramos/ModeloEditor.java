import java.util.ArrayList;
import java.util.Stack;

public class ModeloEditor {
	ArrayList<Figura> figuras = new ArrayList<Figura>();
	Modo modo = new ModoRetangulo(this);
	Stack<Comando> feitos = new Stack<Comando>();
	Stack<Comando> desfeitos = new Stack<Comando>();
	Cor cor = Cor.rgb(1.0, 0.0, 0.0);

	public void retangulo() {
		trocaModo(new ModoRetangulo(this));
	}

	public void circulo() {
		trocaModo(new ModoCirculo(this));
	}

	public void triangulo() {
		trocaModo(new ModoTriangulo(this));
	}

	public void mover() {
		trocaModo(new ModoMover(this));
	}

	private void trocaModo(Modo modo) {
		this.modo.abortar();
		this.modo = modo;
	}

	public void desfazer() {
		if (!feitos.isEmpty()) {
			Comando cmd = feitos.pop();
			cmd.desfazer(this);
			desfeitos.push(cmd);
		}
	}

	public void refazer() {
		if (!desfeitos.isEmpty()) {
			Comando cmd = desfeitos.pop();
			cmd.refazer(this);
			feitos.push(cmd);
		}
	}

	public void apagar() {
		modo = new ModoApagar(this);
	}

	public void inicio(int x, int y) {
		modo.inicio(x, y);
	}

	public void meio(int x, int y) {
		modo.meio(x, y);
	}

	public void fim(int x, int y) {
		modo.fim(x, y);
	}
}