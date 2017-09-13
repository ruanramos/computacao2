public abstract class Modo {
	ModeloEditor modelo;

	public Modo(ModeloEditor modelo) {
		this.modelo = modelo;
	}

	public abstract void inicio(int x, int y);

	public abstract void meio(int x, int y);

	public void fim(int x, int y) {
		meio(x, y);
	}

	public void abortar() {

	}

	public abstract String tag();
}
