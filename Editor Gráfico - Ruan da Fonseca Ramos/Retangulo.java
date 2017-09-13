public class Retangulo extends Figura {
    int larg, alt;
    
    public Retangulo(int x, int y, int larg, int alt) {
        super(x, y);
        this.larg = larg; this.alt = alt;
    }
    
    public void desenhar(DesenhoFigura df) {
        df.desenhar(this);
    }
    
    public boolean dentro(int x, int y) {
        return x >= this.x && x < this.x + larg &&
               y >= this.y && y < this.y + alt;
    }
    
    public String toString() {
        return String.format("Retangulo(%d,%d,%d,%d)", x, y, larg, alt);
    }
    
    public boolean equals(Object o) {
        if(o instanceof Retangulo) {
            Retangulo or = (Retangulo)o;
            return x == or.x && y == or.y && larg == or.larg && alt == or.alt;
        } else return false;
    }
    
    public int hashCode() {
        return x & y & larg & alt;
    }
}