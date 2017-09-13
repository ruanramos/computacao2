public class Canvas implements Componente {
    int x, y, larg, alt;
    Cor frente, fundo;
    ObservadorCanvas oc;
    Tela tela;
    
    public Canvas(int x, int y, int larg, int alt, Cor frente, Cor fundo, ObservadorCanvas oc) {
        this.x = x; this.y = y;
        this.larg = larg; this.alt = alt;
        this.frente = frente; this.fundo = fundo;
        this.oc = oc;
    }
    
    public boolean dentro(int x, int y) {
        return x >= this.x && x < this.x + larg &&
               y >= this.y && y < this.y + alt;
    }
    
    public void desenhar(Tela t) {
        t.retangulo(x, y, larg, alt, frente);
        t.retangulo(x + 3, y + 3, larg - 6, alt - 6, fundo);
        tela = t;
        oc.desenhar(this);
    }
    
    public void clique(int x, int y) {}
    
    public void arrasto(int x, int y) {
        oc.arrasto(x - this.x - 3, y - this.y - 3);
    }

    public void aperto(int x, int y) {
        oc.aperto(x - this.x - 3, y - this.y - 3);
    }
    
    public void solta(int x, int y) {
        oc.solta(x - this.x - 3, y - this.y - 3);
    }
    
    public void retangulo(int x, int y, int larg, int alt, Cor cor) {
        tela.retangulo(x + this.x + 3, y + this.y + 3, larg, alt, cor);
    }
    
    public void circulo(int x, int y, int raio, Cor cor) {
        tela.circulo(x + this.x + 3, y + this.y + 3, raio, cor);
    }
    
    public void triangulo(int x1, int y1, int x2, int y2, int x3, int y3, Cor cor) {
        tela.triangulo(x1 + this.x + 3, y1 + this.y + 3, x2 + this.x + 3, y2 + this.y + 3, x3 + this.x + 3, y3 + this.y + 3, cor);
    }
}
