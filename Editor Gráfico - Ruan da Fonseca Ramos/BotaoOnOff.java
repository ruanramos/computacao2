public class BotaoOnOff extends Botao {
    OnOff onoff;
    
    public BotaoOnOff(int x, int y, int larg, int alt, Cor frente, Cor fundo, String texto, Acao acao, OnOff onoff) {
        super(x, y, larg, alt, frente, fundo, texto, acao);
        this.onoff = onoff;
    }
    
    public void desenhar(Tela t) {
        if(onoff.valor()) {
            t.retangulo(x, y, larg, alt, frente);
            t.texto(texto, x, y, larg, alt, Tela.CENTRO, 36, fundo);
        } else super.desenhar(t);
    }
}
