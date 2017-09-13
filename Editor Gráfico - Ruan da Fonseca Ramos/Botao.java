public class Botao implements Componente {
    int x, y, larg, alt;
    Cor frente, fundo;
    String texto;
    Acao acao;
    
    public Botao(int x, int y, int larg, int alt, Cor frente, Cor fundo, String texto, Acao acao) {
        this.x = x; this.y = y;
        this.larg = larg; this.alt = alt;
        this.frente = frente; this.fundo = fundo;
        this.texto = texto;
        this.acao = acao;
    }
    
    public boolean dentro(int x, int y) {
        return x >= this.x && x < this.x + larg &&
               y >= this.y && y < this.y + alt;
    }
    
    public void desenhar(Tela t) {
        t.retangulo(x, y, larg, alt, frente);
        t.retangulo(x + 3, y + 3, larg - 6, alt - 6, fundo);
        t.texto(texto, x, y, larg, alt, Tela.CENTRO, 36, frente);
    }
    
    public void clique(int x, int y) {
        acao.executa();
    }
    
    public void arrasto(int x, int y) {}
    public void aperto(int x, int y) {
        Cor temp = frente;
        frente = fundo;
        fundo = temp;
    }
    public void solta(int x, int y) {
        Cor temp = frente;
        frente = fundo;
        fundo = temp;
    }
}