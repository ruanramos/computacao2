public class CaixaTexto implements Componente {
     Texto texto;
     int x, y, larg, alt, pos;
     Cor frente;
     
     public CaixaTexto(int x, int y, int larg, int alt, Cor frente, int pos, Texto texto) {
         this.x = x; this.y = y; this.larg = larg; this.alt = alt;
         this.frente = frente;
         this.texto = texto; this.pos = pos;
     }

     public boolean dentro(int x, int y) {
         return false;
     }
     
     public void desenhar(Tela t) {
        t.texto(texto.valor(), x + 8, y + 8, larg - 16, alt - 16, pos, 36, frente);
     }
     
     public void arrasto(int x, int y) {}
     public void aperto(int x, int y) {}
     public void solta(int x, int y) {}
     public void clique(int x, int y) {}
}