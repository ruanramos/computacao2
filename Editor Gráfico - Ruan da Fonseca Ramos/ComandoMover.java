public class ComandoMover implements Comando {
    Figura fig;
    int dx, dy;
    
    public ComandoMover(Figura fig, int dx, int dy) {
        this.fig = fig; this.dx = dx; this.dy = dy;
    }
    
    public void desfazer(ModeloEditor modelo) {
        fig.mover(-dx, -dy);
    }

    public void refazer(ModeloEditor modelo) {
        fig.mover(dx, dy);
    }
}