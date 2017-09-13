public interface Componente {
	boolean dentro(int x, int y);

	void desenhar(Tela t);

	void clique(int x, int y);

	void aperto(int x, int y);

	void arrasto(int x, int y);

	void solta(int x, int y);
}