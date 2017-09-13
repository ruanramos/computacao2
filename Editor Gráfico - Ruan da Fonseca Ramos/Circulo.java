public class Circulo extends Figura {
    int raio;
    
    public Circulo(int x, int y, int raio) {
        super(x, y);
        this.raio = raio;
    }
    
    public void desenhar(DesenhoFigura df) {
        df.desenhar(this);
    }
    
    public boolean dentro(int x, int y) {
        return Math.sqrt(Math.pow(x - this.x, 2) + Math.pow(y - this.y, 2)) <= raio;
    }
}