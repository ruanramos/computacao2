import java.util.ArrayList;

public class ModoTriangulo extends Modo {
	Figura foco;
	int xorig, x, yorig, y;
	ArrayList<Ponto> pontos;

	public ModoTriangulo(ModeloEditor modelo) {
		super(modelo);
		pontos = new ArrayList<Ponto>();
	}

	public void inicio(int x, int y) {

	}

	public void meio(int x, int y) {

	}

	public void fim(int x, int y) {
		super.fim(x, y);
		Ponto p = new Ponto(x, y);
		pontos.add(p);
		modelo.figuras.add(p);
		if (pontos.size() == 3) {
			modelo.figuras.remove(pontos.get(0));
			modelo.figuras.remove(pontos.get(1));
			modelo.figuras.remove(pontos.get(2));
			Triangulo tri = new Triangulo(pontos.get(0), pontos.get(1), pontos.get(2));
			modelo.figuras.add(tri);
			modelo.feitos.push(new ComandoFigura(tri));
			pontos.clear();
		}
	}

	public void abortar() {
		for (Ponto p : pontos) {
			modelo.figuras.remove(p);
		}
		pontos.clear();
	}

	public String tag() {
		return "tri";
	}
}
