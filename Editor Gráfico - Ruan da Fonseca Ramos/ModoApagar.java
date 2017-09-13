
public class ModoApagar extends Modo{
	Figura foco;
	int x, y;
	
	public ModoApagar(ModeloEditor modelo) {
        super(modelo);
    }
	
	public void inicio(int x, int y) {
        for(Figura f: modelo.figuras) {
            if(f.dentro(x, y)) {
                foco = f;
            }
        }
    }
    
    public void meio(int x, int y) {
        // não é necessario meio, apenas o clique e o soltar do clique
    }
    
    public void fim(int x, int y) {
    	modelo.figuras.remove(foco);
        modelo.feitos.push(new ComandoApagar(foco));
    }

    public String tag() {
        return "apg";
    }
}