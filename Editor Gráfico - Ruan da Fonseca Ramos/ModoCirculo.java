
public class ModoCirculo extends Modo {   
    Circulo circ;
    
    public ModoCirculo(ModeloEditor modelo) {
        super(modelo);
    }
    
    public void inicio(int x, int y) {
        circ = new Circulo(x, y, 0);
        modelo.figuras.add(circ);
    }
    
    public void meio(int x, int y) {
        circ.raio = (int)Math.sqrt(Math.pow(x - circ.x, 2) + Math.pow(y - circ.y, 2));
    }
    
    public void fim(int x, int y) {
        super.fim(x, y);
        modelo.feitos.push(new ComandoFigura(circ));
    }

    public String tag() {
        return "circ";
    }
}